package com.example.finalproject.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Person
import com.example.finalproject.PersonRecyclerViewAdapter
import com.example.finalproject.R

class FriendsFragment: Fragment(R.layout.fragment_friends) {

    private lateinit var recyclerView:  RecyclerView
    private lateinit var personRecyclerViewAdapter: PersonRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        personRecyclerViewAdapter = PersonRecyclerViewAdapter(getData())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = personRecyclerViewAdapter





    }



    private fun getData() : List<Person>{

        val list = ArrayList<Person>()

        list.add(
            Person(
                1,
                "Luka Chincharauli",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-9/54434391_581687285669875_1304238695708098560_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=174925&_nc_ohc=fhCj3J2ohC0AX80E94q&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT8HseezwHStyM9G0J7iZcXXVEWTmW0ogOfpETnZuVowlw&oe=620911FA"

            )

        )

        list.add(
            Person(
                2,
                "Dato Kuknishvili",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                3,
                "Giga Dzagania",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t39.30808-6/251422623_3032153303777457_616720896825384224_n.jpg?_nc_cat=108&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=ORqyQd24HXUAX-kqsHH&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT-6Yv4pyuXPAW82LNtvUb3JmS31qiS7TinpM00WfUjvKQ&oe=61E92B66"
            )

        )

        list.add(
            Person(
                4,
                "Nikoloz Katsitadze",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSKbsjf9hq_e6dXYtyCCqNrj64UldmKT7B9paxgtMm4onBFY5IL"
            )

        )

        list.add(
            Person(
                5,
                "Giorgi Jirashvili",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                6,
                "Giorgi Manjavidze",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                7,
                "Giorgi Okriashvili",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                8,
                "Keti Paikidze",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-9/119110999_3216595225130044_3318646894459076928_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=xg245T9_OD0AX8Z_Eug&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT9p7RNTMemZnoFeXNnuR2MjnvN49Ax9IOKYLBCFlQbH7A&oe=620932A6"
            )

        )

        list.add(
            Person(
                9,
                "Kray Brothers",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t1.18169-9/12814160_190986554607554_7161860506537023848_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=qoUXUoW2N_QAX9_REcO&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT-9_sofXbioI1dyWLPqa5ENYGV2sHqRPM8ShAEi3UbB0Q&oe=6208AF3F"
            )

        )

        list.add(
            Person(
                10,
                "Luka Tsatsiashvili",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t1.6435-1/s200x200/189258197_3205617443057660_344593641247000097_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=7206a8&_nc_ohc=VgTSt0jv8OwAX8HodNq&tn=4mJenZ7mmbJT1X-k&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT8UDD2pg7iURuthlNg9Gl4iVPa2z9s9RBP9Ue3YBshY-Q&oe=620812A1"
            )

        )

        list.add(
            Person(
                11,
                "Luka Berikashvili",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-9/191669900_2907191676210191_2672351851643327714_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=0msg2SXy03IAX_twO9b&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT-geYOh5xAqtNVB5kI1fPyiaHU7J8G4hl8cn24j4OXpjg&oe=6207E4B3"
            )

        )

        list.add(
            Person(
                12,
                "Luka Pachulia",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.18169-9/13240672_1730754320542857_2340095290457912498_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=mlVCMAB-ZO4AX_pj7Yw&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT-qjIHOywImXPzYC8OKN0IizYB9dsWHf1DYphCbV5BHog&oe=620A778F"
            )

        )

        list.add(
            Person(
                13,
                "Nika Kokhreidze",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                14,
                "Nika Namoradze",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t39.30808-6/271649233_2665559247074278_1799189119293563196_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=uZsNR7WtCnwAX_O5FRA&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT9tpz3hNjkn7YSAJuS8FEF82a4JLmpE_D4b-OO3ss8jQQ&oe=61E8DE8B"
            )

        )

        list.add(
            Person(
                15,
                "Robert Saakov",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t39.30808-6/242471715_2996938190520188_4232989317878933433_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=NPMVd6S9cmYAX_z0Xkc&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT-qJW74hkQXh8rZkZLO_zlwAbV7R2Er5m27gWgrFccyNw&oe=61E99ACB"
            )

        )

        list.add(
            Person(
                16,
                "Saba Qveladze",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-9/198957759_2043865842444626_9211922250596164992_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=ldqYzshp4cwAX8jAVrL&tn=4mJenZ7mmbJT1X-k&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT8TVEHpCxrKcMgULXAO30cJiKB5UszLYg7Pf-fRndTlzA&oe=6207FA14"
            )

        )

        list.add(
            Person(
                17,
                "Saba Tvaradze",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                18,
                "Sergey Aslanov",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                19,
                "Sulkhan Gabelaia",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-1/s200x200/191399036_3021104494789937_138966943826369861_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=7206a8&_nc_ohc=wQOVnukWeyUAX-TwKw4&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT80Reioj9ZT8C8y7ZzYmPCWqbRigdEU-mKHM-1x2bqVgA&oe=620AB2BA"
            )

        )

        list.add(
            Person(
                20,
                "Tato Chokheli",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-1/p200x200/190160984_1943131095856135_7022672894471282711_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=7206a8&_nc_ohc=xX3rY_O_2HUAX-POhcG&tn=4mJenZ7mmbJT1X-k&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT_pJlILBJrJkFPnBeTW3jAU3oVDbHkMM0JqTk0YElDC1Q&oe=620A387D"
            )

        )

        list.add(
            Person(
                21,
                "Tato Tabatadze",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t1.6435-1/p200x200/185207323_1700484213479472_3598751907282151035_n.jpg?_nc_cat=103&ccb=1-5&_nc_sid=7206a8&_nc_ohc=xZLJg0vMpCEAX9rJTqo&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT9dK_Y_wA-qS9tdzZo1UOR74KjJZOHxG3LD03RgqKogBA&oe=620AC3B8"
            )

        )

        list.add(
            Person(
                22,
                "Tedo Patsatsia",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                23,
                "Zaal Tsagareli",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t39.30808-6/243694916_928972867699468_3589741311288237298_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=LJZt-yfKFqkAX9WEWZ0&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT_o9jzG_HCW9Ap8deCuDrLdxoeiFvKzXlraeRTaI7YZag&oe=61E97B3D"
            )

        )

        list.add(
            Person(
                24,
                "გიორგი შარვაძე",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t1.6435-1/c50.0.200.200a/p200x200/132284747_833354467497462_1930874498663657551_n.jpg?_nc_cat=109&ccb=1-5&_nc_sid=7206a8&_nc_ohc=OsVtfIE28j8AX9sgzCo&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT9S9eXY_25QxkGorpptiWBP96ktjOGsUEt0OafEGqtwKw&oe=620A0248"
            )

        )

        list.add(
            Person(
                25,
                "ლუკა ნებიერიძე",
                "https://scontent.ftbs5-3.fna.fbcdn.net/v/t39.30808-6/242576848_911817073079971_181324140670757752_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=IcP5dDts4vwAX_nvrQ2&_nc_ht=scontent.ftbs5-3.fna&oh=00_AT8_kV5nM96m5DhlEhgHaXBSEIdi80GS-YQm4XAKFWIPIQ&oe=61E9AFAF"
            )

        )

        list.add(
            Person(
                26,
                "Giorgi Okriashvili",
                "data:image/jpeg;base64,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"
            )

        )

        list.add(
            Person(
                27,
                "ჯეკო ხარაბაძე",
                "https://scontent.ftbs5-2.fna.fbcdn.net/v/t1.6435-1/p200x200/189828967_2285088394960721_2175766098077431598_n.jpg?_nc_cat=103&ccb=1-5&_nc_sid=7206a8&_nc_ohc=ZuWpea-OlPQAX8mIB92&_nc_ht=scontent.ftbs5-2.fna&oh=00_AT-EzENueh1rg2W9HwuwximeBZbo6065dquCslMbot4H9w&oe=6207DA72"
            )

        )



        return list;

    }


}