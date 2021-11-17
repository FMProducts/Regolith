package fm.products.regolith.ui.dashboard

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fm.products.regolith.R
import fm.products.regolith.databinding.DashboardFragmentBinding
import fm.products.regolith.ui.recyclerAdapters.CardsRecyclerAdapter

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: DashboardFragmentBinding
    private lateinit var adapter: CardsRecyclerAdapter
    private lateinit var progressBar: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)
        progressBar = ProgressDialog(requireContext())
        progressBar.setMessage(getString(R.string.please_wait))
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        viewModel.items.observe(viewLifecycleOwner){
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        viewModel.showMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.showProgressBar.observe(viewLifecycleOwner){
            if (it) progressBar.show()
            else progressBar.dismiss()
        }

        adapter = CardsRecyclerAdapter(requireContext()).apply {
            onClickAlert = viewModel::showAlert
        }
        binding.cardsRecyclerView.adapter = adapter
        viewModel.loadItems()
    }

}