package classes


data class PlayerState(var gameId: Int,
                         var playerId: Int,
                         var order: Int,
                         var drinksTaken: Int,
                         var maxDrinks: Int)