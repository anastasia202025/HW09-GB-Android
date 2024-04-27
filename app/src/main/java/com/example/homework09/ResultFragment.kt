package com.example.homework09

import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework09.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private val args: ResultFragmentArgs by navArgs()
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater)

        val animSlideInDisplay = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_layout_emergence)
        val layout = binding.root.findViewById<ConstraintLayout>(R.id.fragment_result_layout)
        val controller = LayoutAnimationController(animSlideInDisplay)
        layout.layoutAnimation = controller

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animatorRotate = AnimatorInflater.loadAnimator(requireContext(), R.animator.anim_element_rotate)
        animatorRotate.setTarget(binding.resultTitle)
        animatorRotate.start()

        val animatorFadeInOut = AnimatorInflater.loadAnimator(requireContext(), R.animator.anim_element_fade_in_out)
        animatorFadeInOut.setTarget(binding.buttonReturnQuiz)
        animatorFadeInOut.start()

        binding.result.text = args.number.toString() + " / 4"

        binding.buttonReturnQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}