package com.henriqueaguiar.libraryapi.controller;

import com.henriqueaguiar.libraryapi.controller.dto.AutorDTO;
import com.henriqueaguiar.libraryapi.controller.dto.ErroResposta;
import com.henriqueaguiar.libraryapi.controller.mappers.AutorMapper;
import com.henriqueaguiar.libraryapi.exceptions.OperacaoNaoPermitidaExecption;
import com.henriqueaguiar.libraryapi.exceptions.RegistroDuplicadoException;
import com.henriqueaguiar.libraryapi.model.Autor;
import com.henriqueaguiar.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
// http://localhost:8080/autores
public class AutorController implements GenericControler {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO dto) {

    Autor autor = mapper.toEntity(dto);
    service.salvar(autor);

    // http://localhost:8080/autores/id
    URI location = gerarHeaderLocation(autor.getId());

    return ResponseEntity.created(location).build();


    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
            return service
                    .obterPorId(idAutor)
                    .map(autor -> {
                        AutorDTO dto = mapper.toDto(autor);
                        return ResponseEntity.ok(dto);
                    }).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar (
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){
        List<Autor> resultado = service.pesquisa(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable("id") String id,
            @RequestBody @Valid AutorDTO dto) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());
        service.atualizar(autor);
        return ResponseEntity.noContent().build();
    }

}
