import model.ChatService
import model.User

fun main(args: Array<String>){
    val currentUser = User()
    println(currentUser)
    val recipientOne = User()
    println(recipientOne)
    val recipientTwo = User()
    println(recipientOne)
    val chatService = ChatService(currentUser)


    chatService.createMessage(recipientOne, currentUser,"One")
    chatService.createMessage(recipientOne,currentUser,"Two")
    chatService.createMessage(recipientTwo, currentUser,"Three")
    println(chatService.getChats())

    chatService.editMessage("chatId_2", "messageId_3", "Four")
    println(chatService.getChats())

    chatService.removeMessage("chatId_2", "messageId_3")
    println(chatService.getChats())

    chatService.removeChat("chatId_1")
    println(chatService.getChats())

    chatService.createMessage(recipientOne, currentUser,"One")
    chatService.createMessage(recipientOne,currentUser,"Two")
    chatService.createMessage(currentUser, recipientOne,"Free")
    chatService.createMessage(recipientOne,currentUser,"Four")
    chatService.createMessage(currentUser, recipientOne,"Five")
    chatService.createMessage(recipientOne,currentUser,"Six")
    chatService.createMessage(recipientOne,currentUser,"Seven")
    chatService.createMessage(recipientOne,currentUser,"Eight")
    println(chatService.getChats())
    println(chatService.getListMessage("chatId_3", "messageId_4", 8))
    println(chatService.getChats())

    chatService.createMessage(currentUser, recipientOne, "unreadMessage")
    println(chatService.getUnreadChatsCount())



    }
