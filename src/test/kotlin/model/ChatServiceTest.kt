package model

import org.junit.Test

import org.junit.Assert.*
import org.junit.BeforeClass

class ChatServiceTest {

    @Test
    fun removeChat() {

        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val message = chatService.createMessage(recipientOne, currentUser,"One")
        chatService.removeChat(message.chatId)
        assertTrue(chatService.getChats().isEmpty())
    }

    @Test
    fun getChats() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val message = chatService.createMessage(recipientOne, currentUser,"One")
        assertFalse(chatService.getChats().isEmpty())
    }

    @Test
    fun createMessageNewChat() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val message = chatService.createMessage(recipientOne, currentUser,"One")
        assertFalse(message == null && chatService.getChats().isEmpty())
    }

    @Test
    fun createMessageOldChat() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val messageOne = chatService.createMessage(recipientOne, currentUser,"One")
        val messageTwo = chatService.createMessage(recipientOne, currentUser,"Two")

        assertTrue(messageTwo != null && messageOne.chatId == messageTwo.chatId)
    }

    @Test
    fun editMessage() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val messageOne = chatService.createMessage(recipientOne, currentUser,"One")
        val newText = "NewText"
        chatService.editMessage(messageOne.chatId, messageOne.messageId, newText)
        assertTrue(messageOne.text == newText)
    }

    @Test
    fun removeMessageRemoveChat() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val messageOne = chatService.createMessage(recipientOne, currentUser,"One")
        chatService.removeMessage(messageOne.chatId, messageOne.messageId)
        val chats = chatService.getChats()
        assertTrue(chats.isEmpty())
    }

    @Test
    fun removeMessage() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val messageOne = chatService.createMessage(recipientOne, currentUser,"One")
        val messageTwo = chatService.createMessage(recipientOne, currentUser,"Two")
        chatService.removeMessage(messageOne.chatId, messageOne.messageId)
        val chat = chatService.getChats().first()
        assertFalse(chat.listMessage.contains(messageOne))
    }

    @Test
    fun getUnreadChatsCount() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val messageOne = chatService.createMessage(recipientOne, currentUser,"One")
        val messageTwo = chatService.createMessage(currentUser, recipientOne,"Two")
        chatService.getListMessage(messageOne.chatId, messageOne.messageId, 2)
        val result = chatService.getUnreadChatsCount()

        assertEquals(0, result)
    }

    @Test
    fun getListMessage() {
        val currentUser = User()
        val recipientOne = User()
        val chatService = ChatService(currentUser)

        val messageOne = chatService.createMessage(recipientOne, currentUser,"One")
        val messageTwo = chatService.createMessage(currentUser, recipientOne,"Two")
        val result = chatService.getListMessage(messageOne.chatId, messageOne.messageId, 2)
    }
}