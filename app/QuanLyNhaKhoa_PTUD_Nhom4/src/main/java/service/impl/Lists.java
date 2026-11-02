package service.impl;

import java.util.Collections;
import java.util.List;

final class Lists {

    private Lists() {
    }

    static <T> List<T> orEmpty(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }
}
