package com.aftarfadilah.week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aftarfadilah.week4.R
import com.aftarfadilah.week4.databinding.FragmentStudentDetailBinding
import com.aftarfadilah.week4.util.loadImage
import com.aftarfadilah.week4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentId = arguments?.getString("studentId")

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Fetch data
        viewModel.fetch(studentId ?: "")

        // Observe LiveData
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            // Update UI with student details
            binding.txtID.setText(student.id ?: "")
            binding.txtName.setText(student.name ?: "")
            binding.txtBod.setText(student.bod ?: "")
            binding.txtPhone.setText(student.phone ?: "")
            // You can ignore the button update as per the hint
            var imageView = binding.imageView3
            var progressBar = binding.progressBar
            imageView.loadImage(student.photoUrl, progressBar)
        })
    }

}