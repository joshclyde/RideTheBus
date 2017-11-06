package classes


data class PlayerState(var id: Long,
                        var gameId: Long,
                       var playerId: Long,
                       var turn: Int,
                       var drinksTaken: Int,
                       var maxDrinks: Int)