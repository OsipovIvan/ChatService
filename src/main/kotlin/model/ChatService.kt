package model

class ChatService(
    val currentUser: User,
    private val mapChat: HashMap<String, Chat> = HashMap()
) {

    private fun createOrGetChat(recipient: User, sender: User): Chat{
        val listParticipant = listOf(recipient, sender).sortedBy { it.userId }

        if (mapChat.isNotEmpty()){
            mapChat.forEach {
                if (it.value.listParticipant == listParticipant) return it.value
            }
        }

        val chat = Chat(listParticipant = listParticipant)
        mapChat[chat.chatId] = chat
        return chat
    }

    fun removeChat(chatId: String){
        mapChat.remove(chatId)
    }

    fun getChats(): List<Chat> {
        val listChat = mapChat.getList()
        if (listChat.isEmpty()){
            print("Нет сообщений")
        }
        return listChat
    }

    fun createMessage(recipient: User, sender: User, text: String){
        val chat = createOrGetChat(recipient, sender)
        val message = Message(text = text, sender = sender, recipient = recipient, chatId = chat.chatId)
        chat.listMessage.add(message)
    }

    fun editMessage(chatId: String, messageId: String, text: String) {
        val chat = mapChat[chatId]
        if (chat != null) {
            val message = chat.getMessageById(messageId)
            if (message != null) {
                message.text = text
            }
        }
    }

    fun removeMessage(chatId: String, messageId: String){
        val chat = mapChat[chatId]
        chat?.let {
            val mapMessage = chat.listMessage.filter { it.messageId != messageId}
            if (mapMessage.isEmpty()){
                removeChat(chatId)
            }
        }
    }

    fun getUnreadChatsCount(): Int {
        var count = 0

        mapChat.map {
            it.value
        }.forEach { chat ->
            val listMessage = chat.listMessage
            for (i in 0 until listMessage.size) {
                val message = listMessage[i]
                if (message.recipient == currentUser
                    && !message.isReadMessage
                ) {
                    count++
                    return@forEach
                }
            }
        }

        return count
    }

    fun getListMessage(chatId: String, messageId: String, count: Int): List<Message>? {
        return mapChat[chatId]
            ?.listMessage
            ?.dropWhile { it.messageId != messageId }
            ?.take(count)
            ?.map {
                if (it.recipient == currentUser){
                    it.isReadMessage = true
                }
                it
            }
    }
}

fun <K, V> HashMap<K, V>.getList(): List<V>{
    return this.map { it.value }.toList()
}

fun Chat.getMessageById(messageId: String): Message? {
    return this.listMessage.filter {
        it.messageId == messageId
    }.getOrNull(0)
}




