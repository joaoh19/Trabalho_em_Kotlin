package com.example.guiabairro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.guiabairro.databinding.ActivityDetalhesBinding
import com.example.guiabairro.models.Estabelecimento

class DetalhesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getIntExtra("ID", 0)
        val nome = intent.getStringExtra("NOME") ?: ""
        val tipo = intent.getStringExtra("TIPO") ?: ""
        val descricao = intent.getStringExtra("DESCRICAO") ?: ""
        val telefone = intent.getStringExtra("TELEFONE") ?: ""
        val endereco = intent.getStringExtra("ENDERECO") ?: ""
        val site = intent.getStringExtra("SITE")
        val horario = intent.getStringExtra("HORARIO") ?: ""
        val imagemResId = intent.getIntExtra("IMAGEM", android.R.drawable.ic_dialog_info)


        val estabelecimento = Estabelecimento(
            id = id,
            nome = nome,
            tipo = tipo,
            descricao = descricao,
            telefone = telefone,
            endereco = endereco,
            site = site,
            horarioFuncionamento = horario,
            imagemResId = imagemResId
        )

        setupUI(estabelecimento)
    }

    private fun setupUI(estabelecimento: Estabelecimento) {
        binding.tvNome.text = estabelecimento.nome
        binding.tvTipo.text = estabelecimento.tipo
        binding.tvDescricao.text = estabelecimento.descricao
        binding.tvEndereco.text = estabelecimento.endereco
        binding.tvHorario.text = estabelecimento.horarioFuncionamento
        binding.ivEstabelecimento.setImageResource(estabelecimento.imagemResId)


        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${estabelecimento.telefone}")
            }
            startActivity(intent)
        }


        if (estabelecimento.site != null) {
            binding.btnSite.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(estabelecimento.site)
                }
                startActivity(intent)
            }
        } else {
            binding.btnSite.visibility = View.GONE
        }


        binding.btnCompartilhar.setOnClickListener {
            val shareText = """
                ${estabelecimento.nome}
                ${estabelecimento.descricao}
                Telefone: ${estabelecimento.telefone}
                Endereço: ${estabelecimento.endereco}
                Horário: ${estabelecimento.horarioFuncionamento}
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(intent, "Compartilhar via"))
        }
    }
}