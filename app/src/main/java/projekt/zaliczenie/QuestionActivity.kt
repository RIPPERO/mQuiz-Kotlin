package projekt.zaliczenie

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPosition: Int = 1
    private var questionsList: ArrayList<Questions>? = null
    private var selectedAnswerPosition: Int = 0
    private var correctAnswers: Int = 0
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        userName = intent.getStringExtra(Constants.username)
        questionsList = QuestionList.getQuestion()

        progress_bar.max = questionsList!!.size

        setQuestion()

        answer_one.setOnClickListener(this)
        answer_two.setOnClickListener(this)
        answer_three.setOnClickListener(this)
        answer_four.setOnClickListener(this)

        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion() {
        answer_one.isClickable = true
        answer_two.isClickable = true
        answer_three.isClickable = true
        answer_four.isClickable = true

        val question = questionsList!!.get(currentPosition - 1)

        defaultOptionsView()

        progress_bar.progress = currentPosition
        progress_text.text = "$currentPosition" + "/" + progress_bar.max

        question_text.text = question.question
        answer_one.text = question.answerOne
        answer_two.text = question.answerTwo
        answer_three.text = question.answerThree
        answer_four.text = question.answerFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, answer_one)
        options.add(1, answer_two)
        options.add(2, answer_three)
        options.add(3, answer_four)

        btn_submit.text = "Zatwierdź odpowiedź"

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.answer_button
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.answer_one -> {
                selectedOption(answer_one, 1)
            }
            R.id.answer_two -> {
                selectedOption(answer_two, 2)
            }
            R.id.answer_three -> {
                selectedOption(answer_three, 3)
            }
            R.id.answer_four -> {
                selectedOption(answer_four, 4)
            }
            R.id.btn_submit -> {
                if (selectedAnswerPosition == 0) {
                    if (selectedAnswerPosition == 0 && btn_submit.text == "Zatwierdź odpowiedź") {
                        Toast.makeText(
                            this,
                            "Nie zaznaczono odpowiedzi!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        when {
                            currentPosition <= questionsList!!.size -> {
                                setQuestion()
                            }
                            else -> {
                                val resultActivity = Intent(this, ResultActivity::class.java)
                                resultActivity.putExtra(Constants.username, userName)
                                resultActivity.putExtra(Constants.correctAnswers, correctAnswers)
                                resultActivity.putExtra(
                                    Constants.maxQuestions,
                                    questionsList!!.size
                                )
                                startActivity(resultActivity)
                                finish()
                            }
                        }
                    }

                } else {
                    val question = questionsList?.get(currentPosition - 1)

                    if (question!!.correctAnsw != selectedAnswerPosition) {
                        answer(selectedAnswerPosition, R.drawable.answer_button_incorrect)
                    } else {
                        correctAnswers++
                    }

                    answer(question.correctAnsw, R.drawable.answer_button_correct)

                    answer_one.isClickable = false
                    answer_two.isClickable = false
                    answer_three.isClickable = false
                    answer_four.isClickable = false

                    if (currentPosition == questionsList!!.size) {
                        btn_submit.text = "Zakończ quiz"
                    } else {
                        btn_submit.text = "Następne pytanie"
                    }

                    selectedAnswerPosition = 0
                    currentPosition++
                }
            }
        }
    }

    private fun answer(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                answer_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                answer_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                answer_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                answer_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun selectedOption(view: TextView, selectedOption: Int) {
        defaultOptionsView()
        selectedAnswerPosition = selectedOption

        view.setTextColor(Color.parseColor("#FFFFFF"))
        view.setTypeface(view.typeface, Typeface.BOLD)
        view.background = ContextCompat.getDrawable(
            this,
            R.drawable.answer_button_clicked
        )
    }
}