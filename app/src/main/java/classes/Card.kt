package classes

data class Card(var id: Long,
                var gameId: Long,
                var diamondOrder: Int,
                var playerId: Long,
                var playerOrder: Int,
                var value: Int)