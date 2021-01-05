package advent2015

import scalaadventutils.Problem

import scala.collection.mutable.ListBuffer

object Day21 {

    def main(args: Array[String]) {
        val me   = new Player(0, 0, 100)
        val boss = new Player(8, 2, 109)

        println(part1(me, boss))
        println(part2(me, boss))
    }

    def part1(me: Player, boss: Player): Int = {
        val (weapons, armour, rings) = shop()

        var winCosts = for {
            w  <- weapons
            a  <- armour
            r1 <- rings
            r2 <- rings.filterNot(r => r == r1)
        } yield {
            me.equipWeapon(w)
            me.equipArmour(a)
            me.equipRing(r1)
            me.equipRing(r2)

            if (fight(me, boss) == me) me.totalSpend else Int.MaxValue
        }

        return winCosts.min
    }

    def part2(me: Player, boss: Player): Int = {
        val (weapons, armour, rings) = shop()

        var loseCosts = for {
            w  <- weapons
            a  <- armour
            r1 <- rings
            r2 <- rings.filterNot(r => r == r1)
        } yield {
            me.equipWeapon(w)
            me.equipArmour(a)
            me.equipRing(r1)
            me.equipRing(r2)

            if (fight(me, boss) == boss) me.totalSpend else 0
        }

        return loseCosts.max
    }

    def fight(p1: Player, p2: Player): Player = {
        var p1Damage = p1.damageScore - p2.armourScore
        var p2Damage = p2.damageScore - p1.armourScore

        if (p1Damage < 1) {
            p1Damage = 1
        }

        if (p2Damage < 1) {
            p2Damage = 1
        }

        val p1Turns = Math.ceil(p2.hp / p1Damage)
        val p2Turns = Math.ceil(p1.hp / p2Damage)

        return if (p1Turns <= p2Turns) p1 else p2
    }

    private def shop(): (List[Weapon], List[Armour], List[Ring]) = {
        return (
            List[Weapon](
                new Weapon("Dagger",      8, 4),
                new Weapon("Shortsword", 10, 5),
                new Weapon("Warhammer",  25, 6),
                new Weapon("Longsword",  40, 7),
                new Weapon("Greataxe",   74, 8)
            ),
            List[Armour](
                new Armour("None",        0, 0),
                new Armour("Leather",    13, 1),
                new Armour("Chainmail",  31, 2),
                new Armour("Splintmail", 53, 3),
                new Armour("Bandedmail", 75, 4),
                new Armour("Platemail", 102, 5),
            ),
            List[Ring](
                new Ring("None",        0, 0, 0),
                new Ring("None",        0, 0, 0),
                new Ring("Damage +1",  25, 1, 0),
                new Ring("Damage +2",  50, 2, 0),
                new Ring("Damage +3", 100, 3, 0),
                new Ring("Defense +1", 20, 0, 1),
                new Ring("Defense +2", 40, 0, 2),
                new Ring("Defense +3", 80, 0, 3),
            )
        )
    }
}

class Player(var damageScore: Int, var armourScore: Int, val hp: Int) {

    var totalSpend = 0
    var weapon: Weapon = _
    var armour: Armour = _
    var rings = ListBuffer[Ring]()

    def equipWeapon(w: Weapon) = {
        weapon = w
        calculateDamage()
        calculateSpend()
    }

    def equipArmour(a: Armour) = {
        armour = a
        calculateArmour()
        calculateSpend()
    }

    def equipRing(r: Ring) = {
        if (rings.size >= 2) {
            rings = ListBuffer[Ring]()
        }
        rings += r

        calculateDamage()
        calculateArmour()
        calculateSpend()
    }

    private def calculateDamage() = {
        damageScore = weapon.damage + rings.map(_.damage).sum
    }

    private def calculateArmour() = {
        armourScore = armour.armour + rings.map(_.armour).sum
    }

    private def calculateSpend() = {
        if (armour == null) {
            totalSpend = weapon.cost + rings.map(_.cost).sum
        } else if (weapon == null)  {
            totalSpend = armour.cost + rings.map(_.cost).sum
        } else {
            totalSpend = weapon.cost + armour.cost + rings.map(_.cost).sum
        }
    }
}

class Item(name: String, cost: Int)

class Weapon(val name: String, val cost: Int, val damage: Int)
    extends Item(name, cost)

class Armour(val name: String, val cost: Int, val armour: Int)
    extends Item(name, cost)

class Ring(val name: String, val cost: Int, val damage: Int, val armour: Int)
    extends Item(name, cost)
