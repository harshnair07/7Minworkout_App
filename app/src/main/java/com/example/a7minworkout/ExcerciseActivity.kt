package com.example.a7minworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minworkout.databinding.ActivityExcerciseBinding
import java.util.*
import javax.net.ssl.SSLEngineResult
import kotlin.collections.ArrayList

class ExcerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    private var binding:ActivityExcerciseBinding?=null
    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var tts:TextToSpeech?=null
    private var player:MediaPlayer?=null
    private var ExcerciseTimer:CountDownTimer?=null
    private var ExcerciseProgress=0
    private var ExcerciseList:ArrayList<Excercise_Model>?=null
    private var currentExcercisePosition=-1
    private var excerciseAdapter:ExcerciseStatusAdapter?=null
    private var restTimerDuration:Long=1
     private var excerciseTimerDuration:Long=1


    override fun onCreate(savedInstanceState:Bundle?) {
        binding=ActivityExcerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        ExcerciseList=Constants.defaultExcerciseList()
        tts=TextToSpeech(this,this)
       binding?.toolbarExercise?.setNavigationOnClickListener{
           onBackPressed()
       }
       // binding?.flProgressBar?.visibility=View.INVISIBLE
        setupRestView()
        setUpExcerciseStatusrecyclerView()

    }
    private fun setupRestView(){
        try {
            val soundURI=Uri.parse(
                "android.resource://com.example.a7minwokout/"+R.raw.notification_sound)
                 player=MediaPlayer.create(applicationContext,soundURI)
                player?.isLooping=false
               player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        binding?.flRestView?.visibility=View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvexcerciseName?.visibility=View.INVISIBLE
        binding?.flExcerciseView?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility=View.VISIBLE
        binding?.tvUpcomingExcerciseName?.visibility=View.VISIBLE


        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        binding?.tvUpcomingExcerciseName?.text=
            ExcerciseList!![currentExcercisePosition +1].getName()
        setRestProgressBar()

    }
    private fun setUpExcerciseView(){
       binding?.flRestView?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvexcerciseName?.visibility=View.VISIBLE
        binding?.flExcerciseView?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE
        binding?.tvUpcomingLabel?.visibility=View.INVISIBLE
        binding?.tvUpcomingExcerciseName?.visibility=View.INVISIBLE


        if (ExcerciseTimer!=null){
            ExcerciseTimer?.cancel()
            ExcerciseProgress=0
        }
        speakOut(ExcerciseList!![currentExcercisePosition].getName() )
        binding?.ivImage?.setImageResource(ExcerciseList!!
                [currentExcercisePosition].getImage())
        binding?.tvexcerciseName?.text=
            (ExcerciseList!![currentExcercisePosition].getName())
          setExcerciseProgressBar()

    }
    private fun setUpExcerciseStatusrecyclerView(){
        binding?.rvexcerciseStatus?.layoutManager=
            LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false)
            excerciseAdapter= ExcerciseStatusAdapter(ExcerciseList!!)
            binding?.rvexcerciseStatus?.adapter=excerciseAdapter
    }

    private fun setRestProgressBar(){
        binding?.ProgressBar?.progress=restProgress
        restTimer=object  : CountDownTimer (restTimerDuration*10000,1000){
            override fun onTick(p0:Long) {
               restProgress++
                 binding?.ProgressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()

            }
            override fun onFinish() {
                currentExcercisePosition++
                ExcerciseList!![currentExcercisePosition].setIsSelected(true)
                excerciseAdapter!!.notifyDataSetChanged()
                setUpExcerciseView()
            }
        }.start()
    }

    private fun setExcerciseProgressBar(){
        binding?.ProgressBarExcercise?.progress=ExcerciseProgress
        ExcerciseTimer=object  : CountDownTimer (excerciseTimerDuration*10000,1000){
            override fun onTick(p0:Long) {
                ExcerciseProgress++
                binding?.ProgressBarExcercise?.progress=10-ExcerciseProgress
                binding?.tvExcercise?.text=(10-ExcerciseProgress).toString()

            }
            override fun onFinish() {
                ExcerciseList!![currentExcercisePosition].setIsSelected(false)
                ExcerciseList!![currentExcercisePosition].setIsCompleted(true)
                excerciseAdapter!!.notifyDataSetChanged()

                if (currentExcercisePosition <ExcerciseList?.size!!-1){
                    setupRestView()
                }else{

                    Toast.makeText(
                        this@ExcerciseActivity,
                        "Burned Good Calories Today!!",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if (ExcerciseTimer!=null){
            ExcerciseTimer?.cancel()
            ExcerciseProgress=0
        }
        if (tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if (player!=null){
            player!!.stop()
        }
        binding=null
    }

    override fun onInit(status:Int) {
        if(status==TextToSpeech.SUCCESS){
            val  result=tts?.setLanguage(Locale.UK)
            if (result==TextToSpeech.LANG_MISSING_DATA||
                result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The specified language not supported!!")
            }else{
                Log.e("TTS","Failed")
            }
        }
    }
    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,
            null,"")

    }
}