package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

/**
 * intent? Ela não é uma propriedade da DetailActivity,
 * mas uma propriedade de qualquer atividade.
 * Ela mantém uma referência à intent usada para iniciar a atividade.
 *
 * A propriedade "extras" é do tipo Bundle e, como você pode imaginar,
 * fornece uma maneira de acessar todos os extras transmitidos à intent.
 *
 * Essas duas propriedades estão marcadas com um ponto de interrogação.
 * Por quê? A razão é que as propriedades intent e extras são anuláveis(null),
 * o que significa que elas podem ter um valor ou não.
 *
 *  Em Kotlin, null significa a ausência de um valor. O objeto pode existir ou
 *  pode ser null. Se o app tentar acessar uma propriedade ou chamar uma função
 *  em um objeto null, ele falhará. Para acessar esse valor com segurança,
 *  coloque um "?" após o nome dele. Se a intent for null, o app não tentará acessar
 *  a propriedade "extras". Se a extras for nula, seu código não tentará chamar getString().
 *
 *  Como saber quais propriedades exigem um ponto de interrogação para garantir a proteção
 *  contra valores nulos? Você saberá isso se o nome do tipo for seguido por um
 *  ponto de interrogação ou de exclamação.*/

class WordListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var letterId: String

    /**
     *  Um objeto complementar é semelhante a outros objetos, como instâncias de uma classe.
     *  No entanto, só existe uma instância de um objeto complementar durante a execução do programa.
     *
     *  Isso é semelhante à definição de uma classe, exceto pelo uso da palavra-chave object.
     *  Há também uma palavra-chave companion, que significa que o objeto está associado à
     *  classe DetailActivity, e não é necessário dar um nome de tipo separado a ele.*/
    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Como é possível que arguments sejam opcionais, observe que você chama let() e transmite um lambda.
        /*O que é exatamente um Bundle? Pense nele como um par de chave-valor usado
         para transmitir dados entre classes, como atividades e fragmentos.*/
        arguments?.let{
            letterId = it.getString(LETTER).toString()
        }
    }
    //Inflar o layout no onCreateView em vez do onCreate
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
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}