/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
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

        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String
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
        val letterId = intent?.extras?.getString(LETTER).toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}