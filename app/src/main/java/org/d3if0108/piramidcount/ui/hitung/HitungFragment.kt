package org.d3if0108.piramidcount.ui.hitung

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0108.piramidcount.R
import org.d3if0108.piramidcount.databinding.FragmentHitungBinding
import org.d3if0108.piramidcount.db.LimasDb
import org.d3if0108.piramidcount.model.HasilLimas
import org.d3if0108.piramidcount.ui.penemu.PenemuActivity

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = LimasDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //setContentView(R.layout.activity_main)

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitungLimas() }
        binding.shareButton.setOnClickListener { shareData() }
        binding.btnAPI.setOnClickListener { lanjutAPI() }

        viewModel.getHasilLimas().observe(requireActivity(), { showResult(it) })

    }

    private fun lanjutAPI() {
        val lanjut = Intent(requireContext(), PenemuActivity::class.java)
        startActivity(lanjut)
    }

    @SuppressLint("StringFormatMatches")
    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.ePanjang.text,
            binding.eLebar.text,
            binding.eTinggi.text,
            binding.hasil.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //editPanjang = findViewById(R.id.ePanjang)
        //editLebar = findViewById(R.id.eLebar)
        //editTinggi = findViewById(R.id.eTinggi)
        //buttonHitung = findViewById(R.id.btnHitung)
        //textHasil = findViewById(R.id.hasil)

        //buttonHitung.setOnClickListener(this)


    private fun hitungLimas() {
        val editPanjang = binding.ePanjang.text.toString()
        if (TextUtils.isEmpty(editPanjang.toString())){
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()

            return
        }

        val editlebar = binding.eLebar.text.toString()
        if (TextUtils.isEmpty(editlebar.toString())){
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val editTinggi = binding.eTinggi.text.toString()
        if (TextUtils.isEmpty(editTinggi.toString())){
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLimas(
            editPanjang.toFloat(),
            editTinggi.toFloat(),
            editlebar.toFloat()
        )
    }

    private fun showResult(result: HasilLimas?){
        if (result == null) return
        binding.hasil.text = getString(R.string.hasil_x, result.volumeLimas)
        binding.buttonGroup.visibility = View.VISIBLE
    }

    //override fun onClick(v: View?) {
        //if(v != null){
            //if (v.id == R.id.btnHitung){
                //val masukkanPanjang = editPanjang.text.toString().trim()
                //val masukkanLebar = editLebar.text.toString().trim()
                //val masukkanTinggi = editTinggi.text.toString().trim()

                //var kolomKosong = false
                //if(masukkanPanjang.isEmpty()){
                    //kolomKosong = true
                    //editPanjang.error = "KOLOM INI HARUS DIISI!"
                //}
                //if(masukkanLebar.isEmpty()) {
                    //kolomKosong = true
                    //editLebar.error = "KOLOM INI HARUS DIISI!"
                //}
                //if(masukkanTinggi.isEmpty()) {
                    //kolomKosong = true
                    //editTinggi.error = "KOLOM INI HARUS DIISI!"
                //}
                //if (!kolomKosong){
                    //val volumeLimas = (masukkanPanjang.toDouble()*masukkanLebar.toDouble()*masukkanTinggi.toDouble())/3
                    //textHasil.text = volumeLimas.toString()
                //}
            //}
        //}
    //}
}