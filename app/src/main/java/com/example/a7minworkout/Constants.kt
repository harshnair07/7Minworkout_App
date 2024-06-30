package com.example.a7minworkout

object Constants {
    fun defaultExcerciseList():ArrayList<Excercise_Model>{
        val excerciseList=ArrayList<Excercise_Model>()
        val jumpingJacks=Excercise_Model(
            1,
            "Jumping Jack",
            R.drawable.jumping_jacks,
            false,
            false
        )
        excerciseList.add(jumpingJacks)
        val pushups=Excercise_Model(
            2,
            "Push-Ups",
            R.drawable.push_ups,
            false,
            false
        )
        excerciseList.add(pushups)

        val lunges=Excercise_Model(
            3,
            "Lunges",
            R.drawable.lunges,
            false,
            false
        )
        excerciseList.add(lunges)
        val squats=Excercise_Model(
            4,
            "Squats",
            R.drawable.squats,
            false,
            false
        )
        excerciseList.add(squats)
        val SideSquats=Excercise_Model(
            5,
            "Side Squats",
            R.drawable.side_squats,
            false,
            false
        )
        excerciseList.add(SideSquats)
        val tricepDip=Excercise_Model(
            6,
            "Tricep-Dip",
            R.drawable.tricep_dip,
            false,
            false
        )
        excerciseList.add(tricepDip)
        val wallSit=Excercise_Model(
            7,
            "Wall-Sit",
            R.drawable.wall_sit,
            false,
            false
        )
        excerciseList.add(wallSit)



        return excerciseList
    }
}