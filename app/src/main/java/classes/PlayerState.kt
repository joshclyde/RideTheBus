package classes


data class PlayerState(var id: Int,
                        var gameId: Int,
                       var playerId: Int,
                       var turn: Int,
                       var drinksTaken: Int,
                       var maxDrinks: Int)