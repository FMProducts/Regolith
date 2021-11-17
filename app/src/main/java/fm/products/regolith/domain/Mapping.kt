package fm.products.regolith.domain

import fm.products.regolith.domain.networking.response.BonusResponse
import fm.products.regolith.domain.networking.response.GradeResponse
import fm.products.regolith.domain.networking.response.ProfitResponse
import fm.products.regolith.domain.networking.response.RefillResponse
import fm.products.regolith.ui.recyclerAdapters.*

fun BonusResponse.convertToBonusData(): BonusItemData {
    return BonusItemData(totalRUB, totalUSD, team, refillUSD, refillRUB, about)
}

fun GradeResponse.convertToGradeData(): GradeItemData {
    return GradeItemData(total, raise, stock, balanceUDS, balanceRUB, about)
}

fun RefillResponse.convertToRefillData(): RefillItemData {
    return RefillItemData(total, withdrawn, refillUSD, refillRUB, about)
}


fun ProfitResponse.convertToProfitData(): ProfitItemData {
    return ProfitItemData(total, raise, invest, price, about)
}