package com.cleanarch.moviesearch.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.cleanarch.moviesearch.databinding.FragmentHomeBinding
import com.cleanarch.moviesearch.presentation.viewmodel.MainViewModel

//@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: MainViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome

//        lifecycleScope.launchWhenResumed {
//            viewModel.greetingMsg.onEach {
//                println("===set onEach")
//                textView.text = it
//            }.launchIn(this)
//        }
        lifecycleScope.launchWhenResumed {
            viewModel.greetingMsg.collect {
                textView.text = it
//                return@collect
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}