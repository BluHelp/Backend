package br.senac.bluhelp.dto.project;

import java.util.List;

public record ProjectInformationDTO(String title, List<Long> categories) {

}
