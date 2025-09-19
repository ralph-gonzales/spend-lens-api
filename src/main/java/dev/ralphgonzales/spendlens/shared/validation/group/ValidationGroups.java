package dev.ralphgonzales.spendlens.shared.validation.group;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

public interface ValidationGroups {
    interface Create {}
    interface Update {}

    @GroupSequence({ Update.class, Default.class})
    interface UpdateWithDefault{}
}