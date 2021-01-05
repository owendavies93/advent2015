package advent2015

import scalaadventutils.Problem

import scala.collection.mutable.Set
import scala.util.Random

object Day22 {

    def main(args: Array[String]) {
        println((0 until 1000000).map(_ => part1()).min)
        println((0 until 10000000).map(_ => part2()).min)
    }

    def part1(): Int = {
        val m = new Magician(50, 500, 0, spells(), Set[Spell]())
        val b = new Boss(71, 10, 0)
        return fight(m, b)
    }

    def part2(): Int = {
        val m = new Magician(50, 500, 0, spells(), Set[Spell]())
        val b = new Boss(71, 10, 0)
        return fight(m, b, true)
    }

    def fight(m: Magician, b: Boss, hard: Boolean = false): Int = {
        var usedMana = 0

        while(true) {
            try {
                runEffects(m, b)
                if (b.hp <= 0) {
                    return usedMana
                }

                if (hard) {
                    m.hp -= 1
                    if (m.hp <= 0) {
                        return Int.MaxValue
                    }
                }

                usedMana += m.attack(b)
                if (b.hp <= 0) {
                    return usedMana
                }

                runEffects(m, b)
                if (b.hp <= 0) {
                    return usedMana
                }

                b.attack(m)
                if (m.hp <= 0) {
                    return Int.MaxValue
                }
            } catch {
                case e: LostGameException => return Int.MaxValue
            }
        }

        return Int.MaxValue
    }

    private def runEffects(m: Magician, b: Boss) {
        // reset the armour effects here, because if the spell is
        // still active, it'll get set to the correct value
        m.armour = 0

        m.activeSpells.foreach(s => {
            s.effect match {
                case Some(e) => e.effect(m, b)
                case _       =>
            }
        })

        m.activeSpells = m.activeSpells.map(s => {
            s.effect match {
                case Some(e) => {
                    new Spell(s.name, s.cost, s.damage, s.heal, Some(
                        new Effect(e.turns - 1, e.effect)
                    ))
                }
                case _ => new Spell(s.name, s.cost, s.damage, s.heal, None)
            }
        }).filter(s => {
            s.effect match {
                case Some(e: Effect) => e.turns > 0
                case _               => true
            }
        })
    }

    private def spells(): List[Spell] = {
        return List[Spell](
            new Spell("Magic Missile", 53, 4, 0, None),
            new Spell("Drain",         73, 2, 2, None),
            new Spell("Shield",       113, 0, 0, Some(
                new Effect(6, (m: Magician, b: Boss) => { m.armour = 7 })
            )),
            new Spell("Poison",       173, 0, 0, Some(
                new Effect(6, (m: Magician, b: Boss) => { b.hp -= 3 })
            )),
            new Spell("Recharge",     229, 0, 0, Some(
                new Effect(5, (m: Magician, b: Boss) => { m.mana += 101 })
            )),
        )
    }
}

abstract class Character() {
    var hp: Int
    var armour: Int
    def attack(c: Character): Int
}

class Boss(hp0: Int, damage: Int, a0: Int) extends Character() {
    var hp = hp0
    var armour = a0

    def attack(c: Character): Int = {
        var d = damage - c.armour
        if (d < 1) {
            d = 1
        }
        c.hp -= d
        return damage
    }
}

class Magician
    ( var hp: Int
    , var mana: Int
    , var armour: Int
    , val spells: List[Spell]
    , var activeSpells: Set[Spell]
    ) extends Character() {

    // Pick a random spell to attack with for now
    @throws(classOf[LostGameException])
    def attack(c: Character): Int = {
        val spell = randomSpell(spells.filter(s => {
            s.cost <= mana && !activeSpells.map(_.name).contains(s.name)
        }))

        spell match {
            case Some(s) => {
                c.hp    -= s.damage

                this.hp += s.heal
                this.mana -= s.cost

                s.effect match {
                    case Some(effect) => activeSpells += s
                    case _            =>
                }

                return s.cost
            }
            case None => throw new LostGameException("Not enough mana")
        }
    }

    private def randomSpell(list: List[Spell]): Option[Spell] =
        if (list.isEmpty) None
        else list lift Random.nextInt(list.size)
}

class Spell
    ( val name: String
    , val cost: Int
    , val damage: Int
    , val heal: Int
    , val effect: Option[Effect] = None)

class Effect(var turns: Int, val effect: (Magician, Boss) => Unit)

final case class LostGameException
    ( private val message: String = ""
    , private val cause: Throwable = None.orNull
    ) extends Exception(message, cause)

