package com.nl.ktlint_custom_rules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/10/20.
 */
class AsSafeRule : Rule("as-safe") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == ElementType.AS_KEYWORD) {
            emit(
                node.startOffset,
                "Please use syntax as? to replace as",
                false
            )
        }
    }
}