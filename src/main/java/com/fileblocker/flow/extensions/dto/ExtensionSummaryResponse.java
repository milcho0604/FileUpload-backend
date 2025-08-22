package com.fileblocker.flow.extensions.dto;

import java.util.List;
public record ExtensionSummaryResponse(
        List<String> fixedBlocked, List<String> customAll, List<String> blockedAll
) { }
