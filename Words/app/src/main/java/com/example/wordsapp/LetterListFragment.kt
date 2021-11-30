package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding? = null
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true
    /**
     * Se você tiver certeza de que um valor não será nulo ao acessá-lo, você poderá anexar !! ao nome do tipo.
     * Em seguida, poderá acessá-lo como qualquer outra propriedade, sem o operador ?.
     *
     * Aqui, get() significa que esta propriedade é "somente de acesso".
     * Isso significa que você pode acessar o valor, mas, depois de atribuído
     * (como acontece aqui), não poderá atribuí-lo a outra propriedade.*/
    private val binding get() = _binding!!

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**A classe de vinculação já criou uma propriedade para a recyclerView,
         * e você não precisa chamar findViewById() para cada visualização.*/
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun chooseLayout(){
        /**
         * ao contrário das atividades, um fragmento não é um Context. Não é possível transmiti-lo usando this
         * (referindo-se ao objeto do fragmento) como o contexto do gerenciador de layout. No entanto,
         * os fragmentos oferecem uma propriedade context que pode ser usada para isso.*/
        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        else{
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null){
            return
        }
        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)

        menuItem.icon = when(isLinearLayoutManager){
            true -> ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_off_layout)
            else -> ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }

}