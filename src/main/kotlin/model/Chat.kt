package model

data class Chat(
    val chatId: String = "chatId_${++id}",
    val listParticipant: List<User>,
    val listMessage: MutableList<Message> = mutableListOf()
){
    companion object{
        var id = 0
    }
}
