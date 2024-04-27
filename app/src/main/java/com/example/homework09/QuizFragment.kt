package com.example.homework09

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.homework09.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private lateinit var animFading: Animation
    private lateinit var animEmergenceElements: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater)

        val animSlideInDisplay = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_layout_slide_right_to_left_in_display)
        val layout = binding.root.findViewById<ConstraintLayout>(R.id.fragment_quiz_layout)
        val controller = LayoutAnimationController(animSlideInDisplay)
        layout.layoutAnimation = controller

        animFading = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_layout_fading)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSendAnswers.setOnClickListener {
            val number = getAnswersByUser()
            view.startAnimation(animFading)
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(number)
            findNavController().navigate(action)
        }
    }

    private fun getAnswersByUser() : Int {
        var currentAnswers = 0

        if (binding.radiogroupAnswers01.checkedRadioButtonId == binding.radiobuttonAnswer0103.id)
            currentAnswers++
        if (binding.radiogroupAnswers02.checkedRadioButtonId == binding.radiobuttonAnswer0204.id)
            currentAnswers++
        if (binding.radiogroupAnswers03.checkedRadioButtonId == binding.radiobuttonAnswer0302.id)
            currentAnswers++
        if (binding.radiogroupAnswers04.checkedRadioButtonId == binding.radiobuttonAnswer0401.id)
            currentAnswers++

        return currentAnswers
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}