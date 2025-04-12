package com.students.qb365.ui.subject_mode.writeTest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.students.qb365.R
import com.students.qb365.databinding.BottomSheetTestLayoutBinding
import com.students.qb365.ui.subject_mode.writeTest.adapter.QuestionIndicatorAdapter
import com.students.qb365.ui.subject_mode.writeTest.model.Qst

class QuestionIndicationSheet(val qstList: ArrayList<Qst>) : BottomSheetDialogFragment() {

    private var binding: BottomSheetTestLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetTestLayoutBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            var attempt = 0
            var skipped = 0
            var revised = 0


            for (qst in qstList) {
                if (qst.attempted) {
                    attempt++
                }
                if (qst.skipped) {
                    skipped++
                }
                if (qst.revised) {
                    revised++
                }
            }

            val notAttempt = qstList.size - (attempt + skipped + revised)

            tvAttempted.text = "$attempt attempted"
            tvSkiped.text = "$skipped skipped"
            tvRevised.text = "$revised revised"
            tvNotAttempt.text = "$notAttempt not attempted"


            rvQuestions.layoutManager = GridLayoutManager(requireContext(), 5)
            val adapter = QuestionIndicatorAdapter(qstList)
            rvQuestions.adapter = adapter

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}