package com.example.guiabairro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guiabairro.adapters.EstabelecimentoAdapter
import com.example.guiabairro.databinding.ActivityMainBinding
import com.example.guiabairro.models.Estabelecimento

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var estabelecimentoAdapter: EstabelecimentoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListView()
    }

    private fun setupListView() {
        val estabelecimentos = listOf(
            Estabelecimento(
                id = 1,
                nome = getString(R.string.loja_nome),
                tipo = getString(R.string.loja_tipo),
                descricao = getString(R.string.loja_descricao),
                telefone = "+5511999999999",
                endereco = getString(R.string.loja_endereco),
                site = "https://exemplo-loja.com",
                horarioFuncionamento = getString(R.string.loja_horario),
                imagemResId = android.R.drawable.ic_dialog_email
            ),
        )

        estabelecimentoAdapter = EstabelecimentoAdapter(this, estabelecimentos)
        binding.listViewEstabelecimentos.adapter = estabelecimentoAdapter

        binding.listViewEstabelecimentos.setOnItemClickListener { _, _, position, _ ->
            val estabelecimento = estabelecimentos[position]
            val intent = Intent(this, DetalhesActivity::class.java).apply {
                putExtra("ID", estabelecimento.id)
                putExtra("NOME", estabelecimento.nome)
                putExtra("TIPO", estabelecimento.tipo)
                putExtra("DESCRICAO", estabelecimento.descricao)
                putExtra("TELEFONE", estabelecimento.telefone)
                putExtra("ENDERECO", estabelecimento.endereco)
                putExtra("SITE", estabelecimento.site)
                putExtra("HORARIO", estabelecimento.horarioFuncionamento)
                putExtra("IMAGEM", estabelecimento.imagemResId)
            }
            startActivity(intent)
        }
    }
}