package model

data class User(val userId: String = "userId_${++id}"){
    companion object{
        var id = 0
    }
}
