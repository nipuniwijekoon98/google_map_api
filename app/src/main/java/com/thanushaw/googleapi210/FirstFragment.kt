package com.thanushaw.googleapi210

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import com.thanushaw.googleapi210.adapter.RecyclerViewAdapter
import com.thanushaw.googleapi210.api.University
import com.thanushaw.googleapi210.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<University>()

        // University details
        data.add(University(1,"University of Kelaniya", LatLng(6.9739,79.9157)))
        data.add(University(2,"University of Colombo", LatLng(6.9000,79.8588)))
        data.add(University(3,"University of Peradeniya", LatLng(7.2549,80.5974)))
        data.add(University(4,"University of Jaffna", LatLng(9.6849,80.0220)))
        data.add(University(5,"Rajarata University of Sri Lanka", LatLng(8.3608,80.5033)))
        data.add(University(6,"Wayamba University of Sri Lanka", LatLng(7.3226,79.9882)))
        data.add(University(7,"Sabaragamuwa University of Sri Lanka", LatLng(6.7146,80.7872)))
        data.add(University(8,"University of Sri Jayewardenepura", LatLng(6.8528,79.9036)))
        data.add(University(9,"South Eastern University of Sri Lanka", LatLng(7.2970,81.8500)))
        data.add(University(10,"Uva Wellassa University of Sri Lanka", LatLng(6.9819,81.0763)))
        data.add(University(11,"University of Moratuwa", LatLng(6.7951,79.9009)))

        // This will pass the ArrayList to our Adapter
        val adapter = RecyclerViewAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        adapter.clickEvent.subscribe {
            val clickedId = it.toInt()
            data.forEach{
                if(clickedId == it.id){
                    setFragmentResult(
                        "requestKey",
                        bundleOf(
                            "name" to it.name,
                            "coodinates" to it.latlng
                        )
                    )
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}