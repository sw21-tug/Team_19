package com.tugraz.quizlet.backend.database
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.Question
import io.realm.RealmList
import io.realm.RealmObject
import org.bson.types.ObjectId

class Generator8(){
    fun generateQuestion0(): Question {
        var category = Question_category("Entertainment: Film", "Entertainment: Film")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Golden Retriever")
        listWrong.add("Dalmatian")
        listWrong.add("Shiba Inu")
        var question = Question(ObjectId(), category=category,question="What breed of dog was Marley in the film &quot;Marley &amp; Me&quot; (2008)?", rightAnswer="Labrador Retriever", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion1(): Question {
        var category = Question_category("Entertainment: Video Games", "Entertainment: Video Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("India")
        listWrong.add("Turkey")
        listWrong.add("Slovakia")
        var question = Question(ObjectId(), category=category,question="In the &quot;Halo&quot; franchise, in what country is New Mombasa?", rightAnswer="Kenya", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion2(): Question {
        var category = Question_category("General Knowledge", "General Knowledge")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Oculus")
        listWrong.add("Google")
        listWrong.add("Razer")
        var question = Question(ObjectId(), category=category,question="Which company did Valve cooperate with in the creation of the Vive?", rightAnswer="HTC", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion3(): Question {
        var category = Question_category("Sports", "Sports")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("New York Rangers")
        listWrong.add("Toronto Maple Leafs")
        listWrong.add("Boston Bruins")
        var question = Question(ObjectId(), category=category,question="Which of these teams isn&#039;t a member of the NHL&#039;s &quot;Original Six&quot; era?", rightAnswer="Philadelphia Flyers", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion4(): Question {
        var category = Question_category("Science & Nature", "Science & Nature")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Gold")
        listWrong.add("Silver")
        listWrong.add("Tin")
        var question = Question(ObjectId(), category=category,question="Which element has the chemical symbol &#039;Fe&#039;?", rightAnswer="Iron", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion5(): Question {
        var category = Question_category("Entertainment: Video Games", "Entertainment: Video Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Dragon Ball")
        listWrong.add("Sonic The Hedgehog")
        listWrong.add("Yugioh")
        var question = Question(ObjectId(), category=category,question="Which franchise does the creature &quot;Slowpoke&quot; originate from?", rightAnswer="Pokemon", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion6(): Question {
        var category = Question_category("Entertainment: Television", "Entertainment: Television")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("10 Meters")
        listWrong.add("2 Meters")
        listWrong.add("5 Meters")
        var question = Question(ObjectId(), category=category,question="In the original Doctor Who series (1963), fourth doctor Tom Baker&#039;s scarf was how long?", rightAnswer="7 Meters", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion7(): Question {
        var category = Question_category("General Knowledge", "General Knowledge")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("15 MPH")
        listWrong.add("20 MPH")
        listWrong.add("200 MPH")
        var question = Question(ObjectId(), category=category,question="What is the airspeed velocity of an unladen swallow?", rightAnswer="24 MPH", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion8(): Question {
        var category = Question_category("Art", "Art")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Roy Lichtenstein")
        listWrong.add("David Hockney")
        listWrong.add("Peter Blake")
        var question = Question(ObjectId(), category=category,question="Which artist&rsquo;s studio was known as &#039;The Factory&#039;?", rightAnswer="Andy Warhol", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion9(): Question {
        var category = Question_category("Entertainment: Board Games", "Entertainment: Board Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("24")
        listWrong.add("15")
        listWrong.add("18")
        var question = Question(ObjectId(), category=category,question="How many dots are on a single die?", rightAnswer="21", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion10(): Question {
        var category = Question_category("Animals", "Animals")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Sheep")
        listWrong.add("Camel")
        listWrong.add("Llama")
        var question = Question(ObjectId(), category=category,question="Cashmere is the wool from which kind of animal?", rightAnswer="Goat", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion11(): Question {
        var category = Question_category("Geography", "Geography")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Oregon, Idaho, Nevada, Utah")
        listWrong.add("Kansas, Oklahoma, Arkansas, Louisiana")
        listWrong.add("South Dakota, Minnesota, Nebraska, Iowa")
        var question = Question(ObjectId(), category=category,question="What are the four corner states of the US?", rightAnswer="Utah, Colorado, Arizona, New Mexico", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion12(): Question {
        var category = Question_category("Politics", "Politics")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Chinese Taiwan")
        listWrong.add("Republic of Taiwan")
        listWrong.add("Republic of Taipei ")
        var question = Question(ObjectId(), category=category,question="Due to the Nagoya Resolution, China agreed to allow Taiwan to compete separately in international sporting events under what name?", rightAnswer="Chinese Taipei", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion13(): Question {
        var category = Question_category("Entertainment: Music", "Entertainment: Music")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("MSTRKRFT")
        listWrong.add("STRFKR")
        listWrong.add("SBTRKT")
        var question = Question(ObjectId(), category=category,question="Which of these is the name of an American psychedelic rock band formed in 2002 by Benjamin Goldwasser and Andrew VanWyngarden?", rightAnswer="MGMT", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion14(): Question {
        var category = Question_category("Entertainment: Film", "Entertainment: Film")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Indiana Jones")
        listWrong.add("James Bond")
        listWrong.add("Harry Potter")
        var question = Question(ObjectId(), category=category,question="Which iconic character is featured in movies such as &quot;The Enforcer&quot;, &quot;Sudden Impact&quot;, and &quot;The Dead Pool&quot;?", rightAnswer="Dirty Harry", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion15(): Question {
        var category = Question_category("Geography", "Geography")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Brazil")
        listWrong.add("Colombia")
        listWrong.add("Ecuador")
        var question = Question(ObjectId(), category=category,question="Where are the Nazca Lines located?", rightAnswer="Peru", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion16(): Question {
        var category = Question_category("Entertainment: Japanese Anime & Manga", "Entertainment: Japanese Anime & Manga")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Billy Bat")
        listWrong.add("20th Century Boys")
        listWrong.add("Monster")
        var question = Question(ObjectId(), category=category,question="Which one of these Manga titles was not created by Urasawa Naoki?", rightAnswer="My Father&#039;s Journal", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion17(): Question {
        var category = Question_category("Entertainment: Cartoon & Animations", "Entertainment: Cartoon & Animations")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Seth MacFarlane")
        listWrong.add("Seth Rogen")
        listWrong.add("Seth Rollins")
        var question = Question(ObjectId(), category=category,question="The stop motion comedy show &quot;Robot Chicken&quot; was created by which of the following?", rightAnswer="Seth Green", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion18(): Question {
        var category = Question_category("Entertainment: Cartoon & Animations", "Entertainment: Cartoon & Animations")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Regular Show")
        listWrong.add("Adventure Time")
        listWrong.add("The Amazing World of Gumball")
        var question = Question(ObjectId(), category=category,question="What Cartoon Network show aired its first episode on November 4th, 2013?", rightAnswer="Steven Universe", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion19(): Question {
        var category = Question_category("Entertainment: Film", "Entertainment: Film")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Hill Valley Time Travelers")
        listWrong.add("The Time Travelers")
        listWrong.add("The Lucky Man")
        var question = Question(ObjectId(), category=category,question="What was another suggested name for, the 1985 film, Back to the Future?", rightAnswer="Spaceman From Pluto", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion20(): Question {
        var category = Question_category("Entertainment: Comics", "Entertainment: Comics")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("No Name")
        listWrong.add("Golden City")
        listWrong.add("Yellow Moon")
        var question = Question(ObjectId(), category=category,question="In the Homestuck Series, what is the alternate name for the Kingdom of Lights?", rightAnswer="Prospit", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion21(): Question {
        var category = Question_category("Entertainment: Japanese Anime & Manga", "Entertainment: Japanese Anime & Manga")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Naruto")
        listWrong.add("One Piece")
        listWrong.add("Chibi Maruko-chan")
        var question = Question(ObjectId(), category=category,question="Which of these anime have over 7,500 episodes?", rightAnswer="Sazae-san", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion22(): Question {
        var category = Question_category("Entertainment: Music", "Entertainment: Music")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("MF DOOM")
        listWrong.add("Beyonce")
        listWrong.add("Beck")
        var question = Question(ObjectId(), category=category,question="Kanye West at 2009 VMA&#039;s interrupted which celebrity?", rightAnswer="Taylor Swift", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion23(): Question {
        var category = Question_category("Science: Computers", "Science: Computers")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("??")
        listWrong.add("if then")
        listWrong.add("?")
        var question = Question(ObjectId(), category=category,question="In programming, the ternary operator is mostly defined with what symbol(s)?", rightAnswer="?:", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion24(): Question {
        var category = Question_category("Entertainment: Television", "Entertainment: Television")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Jimmy Fallon")
        listWrong.add("Saturday Night Live")
        listWrong.add("Larry Rubert")
        var question = Question(ObjectId(), category=category,question="Which of these television shows makes everyone look under their chair?", rightAnswer="Oprah", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion25(): Question {
        var category = Question_category("Entertainment: Music", "Entertainment: Music")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("The Bends")
        listWrong.add("Kid A")
        listWrong.add("A Moon Shaped Pool")
        var question = Question(ObjectId(), category=category,question="What was Radiohead&#039;s first album?", rightAnswer="Pablo Honey", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion26(): Question {
        var category = Question_category("Entertainment: Video Games", "Entertainment: Video Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("n")
        listWrong.add("i")
        listWrong.add("m")
        var question = Question(ObjectId(), category=category,question="In the title of the game &quot;Luigi&#039;s Mansion&quot;, what is the only letter to not appear with a pair of eyes in it?", rightAnswer="s", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion27(): Question {
        var category = Question_category("General Knowledge", "General Knowledge")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Digest")
        listWrong.add("Look")
        listWrong.add("Read")
        var question = Question(ObjectId(), category=category,question="The New York Times slogan is, &ldquo;All the News That&rsquo;s Fit to&hellip;&rdquo;", rightAnswer="Print", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion28(): Question {
        var category = Question_category("Entertainment: Film", "Entertainment: Film")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Las Vegas, Nevada")
        listWrong.add("Chicago, Illinois")
        listWrong.add("Orlando, Florida")
        var question = Question(ObjectId(), category=category,question="What city did the monster attack in the film, &quot;Cloverfield&quot;?", rightAnswer="New York, New York", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion29(): Question {
        var category = Question_category("Science & Nature", "Science & Nature")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Watt")
        listWrong.add("Decibel")
        listWrong.add("Kelvin")
        var question = Question(ObjectId(), category=category,question="What is radiation measured in?", rightAnswer="Gray ", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion30(): Question {
        var category = Question_category("History", "History")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Dwight D. Eisenhower")
        listWrong.add("Franklin D. Roosevelt")
        listWrong.add("Herbert Hoover")
        var question = Question(ObjectId(), category=category,question="Which U.S. president took part in the Potsdam Conference, where the Allies reached a peace settlement with Germany?", rightAnswer="Harry S. Truman", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion31(): Question {
        var category = Question_category("Entertainment: Music", "Entertainment: Music")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Swamp Thing")
        listWrong.add("Emoji")
        listWrong.add("BAMF")
        var question = Question(ObjectId(), category=category,question="Which of these is NOT a song by Pegboard Nerds?", rightAnswer="WiFi Tears", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion32(): Question {
        var category = Question_category("Entertainment: Television", "Entertainment: Television")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("10")
        listWrong.add("5")
        listWrong.add("3")
        var question = Question(ObjectId(), category=category,question="How many seasons did the Sci-Fi television show &quot;Stargate Universe&quot; have?", rightAnswer="2", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion33(): Question {
        var category = Question_category("Geography", "Geography")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Helsinki")
        listWrong.add("Tartu")
        listWrong.add("Riga")
        var question = Question(ObjectId(), category=category,question="What is the capital of Estonia?", rightAnswer="Tallinn", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion34(): Question {
        var category = Question_category("Entertainment: Music", "Entertainment: Music")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Nirvana")
        listWrong.add("Coldplay")
        listWrong.add("U2")
        var question = Question(ObjectId(), category=category,question="Which rock band released the album &quot;The Bends&quot; in March 1995?", rightAnswer="Radiohead", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion35(): Question {
        var category = Question_category("Entertainment: Television", "Entertainment: Television")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("The Collective")
        listWrong.add("The Federation")
        listWrong.add("The Rebels")
        var question = Question(ObjectId(), category=category,question="What is the name of the main antagonists in Battlestar Galactica?", rightAnswer="The Cylons", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion36(): Question {
        var category = Question_category("Entertainment: Video Games", "Entertainment: Video Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Yasnaya Polyana")
        listWrong.add("Pochinki")
        listWrong.add("Georgopol")
        var question = Question(ObjectId(), category=category,question="Which of these is NOT a name of a city in the main island of PLAYERUNKNOWN&#039;S BATTLEGROUNDS?", rightAnswer="Belushya Guba", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion37(): Question {
        var category = Question_category("Entertainment: Video Games", "Entertainment: Video Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Strife")
        listWrong.add("League of Legends")
        listWrong.add("Heroes of Newerth")
        var question = Question(ObjectId(), category=category,question="What former MOBA, created by Waystone Games and published by EA, was shut down in 2014?", rightAnswer="Dawngate", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion38(): Question {
        var category = Question_category("Entertainment: Television", "Entertainment: Television")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Bryce Kranyik")
        listWrong.add("Ryan Sutfin")
        listWrong.add("Chris Mundorf")
        var question = Question(ObjectId(), category=category,question="Who was the winner of &quot;Big Brother&quot; Season 10?", rightAnswer="Dan Gheesling", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion39(): Question {
        var category = Question_category("History", "History")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("France")
        listWrong.add("Cuba")
        listWrong.add("United States")
        var question = Question(ObjectId(), category=category,question="Toussaint Louverture led a successful slave revolt in which country?", rightAnswer="Haiti", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion40(): Question {
        var category = Question_category("Science & Nature", "Science & Nature")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("A combat stimulant from WW2")
        listWrong.add("A type of seasoning")
        listWrong.add("A port city in the carribean")
        var question = Question(ObjectId(), category=category,question="What is &quot;Stenoma&quot;?", rightAnswer="A genus of moths", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion41(): Question {
        var category = Question_category("Science & Nature", "Science & Nature")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Four")
        listWrong.add("Two")
        listWrong.add("Six")
        var question = Question(ObjectId(), category=category,question="How many protons are in an oxygen atom?", rightAnswer="Eight", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion42(): Question {
        var category = Question_category("History", "History")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("1907")
        listWrong.add("1899")
        listWrong.add("1917")
        var question = Question(ObjectId(), category=category,question="In what year was the M1911 pistol designed?", rightAnswer="1911", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion43(): Question {
        var category = Question_category("History", "History")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Athena")
        listWrong.add("Zeus")
        listWrong.add("Both Athena and Zeus")
        var question = Question(ObjectId(), category=category,question="The pantheon in Rome was used to worship what god?", rightAnswer="Any god they wanted", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion44(): Question {
        var category = Question_category("Science: Mathematics", "Science: Mathematics")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Abel")
        listWrong.add("Euler")
        listWrong.add("Gauss")
        var question = Question(ObjectId(), category=category,question="Which of the following famous mathematicians died in a duel at the age of 20?", rightAnswer="Galois", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion45(): Question {
        var category = Question_category("History", "History")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("India")
        listWrong.add("Canada")
        listWrong.add("Brazil")
        var question = Question(ObjectId(), category=category,question="Which country did the Eureka Rebellion, an 1856 battle against colonial rule, take place in?", rightAnswer="Australia", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion46(): Question {
        var category = Question_category("Entertainment: Film", "Entertainment: Film")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Patrick Swayze")
        listWrong.add("John Cusack")
        listWrong.add("Harrison Ford")
        var question = Question(ObjectId(), category=category,question="Who plays Jack Burton in the movie &quot;Big Trouble in Little China?&quot;", rightAnswer="Kurt Russell", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion47(): Question {
        var category = Question_category("Entertainment: Film", "Entertainment: Film")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("77 mph")
        listWrong.add("100 mph")
        listWrong.add("70 mph")
        var question = Question(ObjectId(), category=category,question="In the movie &quot;Back to the Future,&quot; what speed does Doc Brown&#039;s DeLorean need to reach in order to travel through time?", rightAnswer="88 mph", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion48(): Question {
        var category = Question_category("History", "History")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("James Wellington")
        listWrong.add("Johnson Hinsin")
        listWrong.add("Boris Heviltik")
        var question = Question(ObjectId(), category=category,question="Who is the creator of the soft drink, Dr. Pepper?", rightAnswer="Charles Alderton", wrongAnswers=listWrong)
        return question
    }

    fun generateQuestion49(): Question {
        var category = Question_category("Entertainment: Video Games", "Entertainment: Video Games")
        var listWrong: RealmList<String> = RealmList()
        listWrong.add("Callie &amp; Marie")
        listWrong.add("Diamond &amp; Aquamarina")
        listWrong.add("DJ Octavio &amp; Crusty Sean")
        var question = Question(ObjectId(), category=category,question="In Splatoon 2, who are the members of Off The Hook?", rightAnswer="Pearl &amp; Marina", wrongAnswers=listWrong)
        return question
    }

    fun foreach(data_handler: (q: Question) -> Unit) {
        data_handler(generateQuestion0())
        data_handler(generateQuestion1())
        data_handler(generateQuestion2())
        data_handler(generateQuestion3())
        data_handler(generateQuestion4())
        data_handler(generateQuestion5())
        data_handler(generateQuestion6())
        data_handler(generateQuestion7())
        data_handler(generateQuestion8())
        data_handler(generateQuestion9())
        data_handler(generateQuestion10())
        data_handler(generateQuestion11())
        data_handler(generateQuestion12())
        data_handler(generateQuestion13())
        data_handler(generateQuestion14())
        data_handler(generateQuestion15())
        data_handler(generateQuestion16())
        data_handler(generateQuestion17())
        data_handler(generateQuestion18())
        data_handler(generateQuestion19())
        data_handler(generateQuestion20())
        data_handler(generateQuestion21())
        data_handler(generateQuestion22())
        data_handler(generateQuestion23())
        data_handler(generateQuestion24())
        data_handler(generateQuestion25())
        data_handler(generateQuestion26())
        data_handler(generateQuestion27())
        data_handler(generateQuestion28())
        data_handler(generateQuestion29())
        data_handler(generateQuestion30())
        data_handler(generateQuestion31())
        data_handler(generateQuestion32())
        data_handler(generateQuestion33())
        data_handler(generateQuestion34())
        data_handler(generateQuestion35())
        data_handler(generateQuestion36())
        data_handler(generateQuestion37())
        data_handler(generateQuestion38())
        data_handler(generateQuestion39())
        data_handler(generateQuestion40())
        data_handler(generateQuestion41())
        data_handler(generateQuestion42())
        data_handler(generateQuestion43())
        data_handler(generateQuestion44())
        data_handler(generateQuestion45())
        data_handler(generateQuestion46())
        data_handler(generateQuestion47())
        data_handler(generateQuestion48())
        data_handler(generateQuestion49())
    }
}