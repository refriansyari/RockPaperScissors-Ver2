package com.refri.rockpaperscissorsthegame.ui.appintropage.nameinput

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.refri.rockpaperscissorsthegame.R
import com.refri.rockpaperscissorsthegame.databinding.FragmentInputNameBinding
import com.refri.rockpaperscissorsthegame.menu.MenuGameActivity
import com.refri.rockpaperscissorsthegame.preference.UserPreference

class InputNameFragment : Fragment() {

    private lateinit var binding: FragmentInputNameBinding

    private val userPreference: UserPreference? by lazy {
        context?.let { UserPreference(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInputNameBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    fun navigateToMenuGame() {
        if (isNameFilled()) {
            userPreference?.name = binding.etPlayerName.text.toString().trim()
            val intent = Intent(context, MenuGameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun isNameFilled(): Boolean {
        val name = binding.etPlayerName.text.toString().trim()
        var isFormValid = true
        if (name.isEmpty()) {
            isFormValid = false
            Snackbar.make(
                binding.root,
                getString(R.string.text_input_name_to_proceed),
                Snackbar.LENGTH_SHORT
            ).show()
        }
        return isFormValid
    }

}