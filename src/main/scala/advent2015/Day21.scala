package advent2015

import scala.collection.mutable.ListBuffer

object Day21 {

    def main(args: Array[String]) = {
    }
}

class Character(damageScore: Int, armourScore: Int, hp: Int)

class Player(var damageScore: Int, var armourScore: Int, var hp: Int)
    extends Character(damageScore, armourScore, hp) {

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
        if (rings.size < 2) {
            rings = rings += r
        } else {
            rings(0) = r
        }

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
        totalSpend = armour.cost + weapon.cost + rings.map(_.cost).sum
    }
}

class Item(name: String, cost: Int)

class Weapon(val name: String, val cost: Int, val damage: Int)
    extends Item(name, cost)

class Armour(val name: String, val cost: Int, val armour: Int)
    extends Item(name, cost)

class Ring(val name: String, val cost: Int, val armour: Int, val damage: Int)
    extends Item(name, cost)
