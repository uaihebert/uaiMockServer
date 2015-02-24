package com.uaihebert.uaimockserver.dto.factory;

import com.uaihebert.uaimockserver.dto.model.UaiFileDTO;
import com.uaihebert.uaimockserver.model.UaiFile;

public final class UaiFileDTOFactory {
    private UaiFileDTOFactory() {
    }

    public static UaiFileDTO create(final UaiFile uaiFile) {
        return new UaiFileDTO(uaiFile.name, uaiFile.fullPath);
    }
}
