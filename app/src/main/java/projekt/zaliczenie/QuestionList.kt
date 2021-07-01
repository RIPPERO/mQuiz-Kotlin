package projekt.zaliczenie

object QuestionList {

    fun getQuestion(): ArrayList<Questions> {
        val questionsList = ArrayList<Questions>()
        val question1 = Questions(
            1,
            "Różańcową tajemnicą chwalebną nie jest?",
            "wniebowzięcie Matki Bożej",
            "zmartwychwstanie Jezusa",
            "śmierć Jezusa na krzyżu",
            "zesłanie Ducha Świętego",
            3
        )
        questionsList.add(question1)

        val question2 = Questions(
            2,
            "Czym jest Bozon Higgsa?",
            "Cząstką elementrną",
            "Potrawą",
            "Działaniem matematycznym",
            "Nie istnieje",
            1
        )
        questionsList.add(question2)

        val question3 = Questions(
            3,
            "Z gry na jakim instrumencie słynie Czesław Mozil?",
            "na kornecie",
            "na djembe",
            "na akordeonie",
            "na ksylofonie",
            3
        )
        questionsList.add(question3)

        val question4 = Questions(
            4,
            "Ile to jest 1111 razy 1111, jeśli 1 razy 1 to 1, a 11 razy 11 to 121?",
            "111 111 111",
            "123 454 321",
            "12 321",
            "1 234 321",
            4
        )
        questionsList.add(question4)

        val question5 = Questions(
            5,
            "Skąd pochodzi Conan Barbarzyńca?",
            "z Cimmerii",
            "z Oz",
            "z Mordoru",
            " z Rivii",
            1
        )
        questionsList.add(question5)

        val question6 = Questions(
            6,
            "Komiksowym „dzieckiem” rysownika Boba Kane’a jest",
            "Superman",
            "Spider-Man",
            "Captain America",
            "Batman",
            4
        )
        questionsList.add(question6)

        val question7 =
            Questions(7, "Rybą nie jest", "krasnopiórka", "kraska", "rozpiór", "świnka", 2)
        questionsList.add(question7)

        val question8 = Questions(
            8,
            "Który aktor urodził się w roku opatentowania kinematografu braci Lumière?",
            "Humphrey Bogart",
            "Charlie Chaplin",
            "Rudolph Valentino",
            "Fred Astaire",
            3
        )
        questionsList.add(question8)

        val question9 = Questions(
            9,
            "Który utwór Juliusza Słowackiego napisany jest prozą?",
            "\"Anhelli\"",
            "\"W Szwajcarii\"",
            "\"Arab\"",
            "\"Godzina myśli\"",
            1
        )
        questionsList.add(question9)

        val question10 =
            Questions(10, "Który ssak się nie poci?", "człowiek", "koń", "owca", "królik", 4)
        questionsList.add(question10)

        return questionsList
    }

}