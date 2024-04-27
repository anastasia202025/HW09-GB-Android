package com.example.homework09

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.example.homework09.databinding.FragmentHelloBinding
import java.text.SimpleDateFormat
import java.util.Calendar

    class HelloFragment : Fragment() {
        private lateinit var binding: FragmentHelloBinding
        private lateinit var animSlide: Animation
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd / MM / yyyy")

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            binding = FragmentHelloBinding.inflate(inflater)
            animSlide = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_layout_slide_right_to_left_for_display)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.buttonStart.setOnClickListener {
                view.startAnimation(animSlide)
                findNavController().navigate(R.id.action_helloFragment_to_quizFragment)
            }

            binding.buttonDateOfBirth.setOnClickListener {
                val constraints = CalendarConstraints.Builder()
                    .setOpenAt(calendar.timeInMillis)
                    .build()

                val dateDialog = MaterialDatePicker.Builder.datePicker()
                    .setCalendarConstraints(constraints)
                    .setTitleText(resources.getString(R.string.input_date_of_birth))
                    .build()


                dateDialog.addOnPositiveButtonClickListener {
                        timeInMillis -> calendar.timeInMillis = timeInMillis
                    Snackbar.make(binding.root, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
                }

                dateDialog.show(parentFragmentManager, "Date of Birth")
            }
        }
    }
