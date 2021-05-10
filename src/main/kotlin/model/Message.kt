package model

data class Message(
    val messageId: String = "messageId_${++id}",
    var text: String,
    val sender: User,
    val recipient: User,
    var isReadMessage: Boolean = false,
    val chatId: String
){
    companion object{
        var id = 0
    }
}
