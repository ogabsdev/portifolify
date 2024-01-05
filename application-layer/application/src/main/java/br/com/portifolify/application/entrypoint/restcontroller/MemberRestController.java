package br.com.portifolify.application.entrypoint.restcontroller;

import br.com.portifolify.application.entrypoint.restcontroller.dto.converter.MemberRestControllerConvert;
import br.com.portifolify.application.entrypoint.restcontroller.dto.response.MemberResponse;
import br.com.portifolify.core.usecase.FindAllMemberUseCase;
import br.com.portifolify.core.usecase.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberRestController {

    private final FindAllMemberUseCase findAllMemberUseCase;

    private final MemberRestControllerConvert memberRestControllerConvert;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers() {

        List<MemberDTO> members = findAllMemberUseCase.find();

        return ResponseEntity.ok(memberRestControllerConvert.convert(members));
    }

}
