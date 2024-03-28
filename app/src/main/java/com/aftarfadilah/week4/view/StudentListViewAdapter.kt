package com.aftarfadilah.week4.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import com.aftarfadilah.week4.R
import com.aftarfadilah.week4.databinding.FragmentStudentListItemBinding
import com.aftarfadilah.week4.model.Student
import com.aftarfadilah.week4.util.loadImage

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class StudentListViewAdapter(
    val studentList:ArrayList<Student>
) : RecyclerView.Adapter<StudentListViewAdapter.StudentViewHolder>() {

    class StudentViewHolder(var binding: FragmentStudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentListViewAdapter.StudentViewHolder {
        val binding = FragmentStudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentListViewAdapter.StudentViewHolder(binding)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id ?: "")
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(studentList[position].photoUrl, progressBar)
    }
    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return studentList.size
    }

}