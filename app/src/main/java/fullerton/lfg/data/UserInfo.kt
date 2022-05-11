package fullerton.lfg.data

import fullerton.lfg.data.model.User

class UserInfo {
    companion object {
        fun dataSet(): ArrayList<User> {
            val userList = ArrayList<User>()
            userList.add(
                User(
                    "Zules",
                    "Perez",
                    "hi@hot.com",
                    "password123"
                )
            )
            userList.add(
                User(
                    "Joshua",
                    "Konechy",
                    "bruh@hotmail.com",
                    "password123"
                )
            )
            userList.add(
                User(
                    "Allen",
                    "Rivas",
                    "sup@hotmail.com",
                    "password123"
                )
            )
            userList.add(
                User(
                    "Minh",
                    "Le",
                    "yo@hotmail.com",
                    "password123"
                )
            )
            userList.add(
                User(
                    "Jason",
                    "Fake",
                    "hi@hotmail.com",
                    "password123"
                )
            )
            userList.add(
                User(
                    "Ryan",
                    "Fake",
                    "wazup@hotmail.com",
                    "password123"
                )
            )
            userList.add(
                User(
                    "Sasha",
                    "Fake",
                    "aloha@hotmail.com",
                    "password123"
                )
            )
            return userList
        }

    }
}