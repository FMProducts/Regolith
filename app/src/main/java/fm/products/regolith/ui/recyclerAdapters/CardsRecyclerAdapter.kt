package fm.products.regolith.ui.recyclerAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fm.products.regolith.R
import fm.products.regolith.databinding.BonusItemBinding
import fm.products.regolith.databinding.GradeItemBinding
import fm.products.regolith.databinding.ProfitItemBinding
import fm.products.regolith.databinding.RefillItemBinding


class CardsRecyclerAdapter(
    context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<CardItemData> = listOf()
    var onClickAlert: ((String) -> Unit)? = null
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CardsItemTypes.GRADE.ordinal -> {
                val binding = GradeItemBinding.inflate(layoutInflater, parent, false)
                GradeItemViewHolder(binding)
            }
            CardsItemTypes.REFILL.ordinal -> {
                val binding = RefillItemBinding.inflate(layoutInflater, parent, false)
                RefillItemViewHolder(binding)
            }
            CardsItemTypes.PROFIT.ordinal -> {
                val binding = ProfitItemBinding.inflate(layoutInflater, parent, false)
                ProfitItemViewHolder(binding)
            }
            else -> {
                val binding = BonusItemBinding.inflate(layoutInflater, parent, false)
                BonusItemViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when (holder) {
            is GradeItemViewHolder -> {
                holder.bind(data as GradeItemData)
                holder.onAlertClick = onClickAlert
            }
            is RefillItemViewHolder -> {
                holder.bind(data as RefillItemData)
                holder.onAlertClick = onClickAlert
            }
            is ProfitItemViewHolder -> {
                holder.bind(data as ProfitItemData)
                holder.onAlertClick = onClickAlert
            }
            is BonusItemViewHolder -> {
                holder.bind(data as BonusItemData)
                holder.onAlertClick = onClickAlert
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        items[position].let {
            return it.itemType.ordinal
        }
    }

    enum class CardsItemTypes {
        GRADE, REFILL, PROFIT, BONUS
    }

}

open class CardItemData(val itemType: CardsRecyclerAdapter.CardsItemTypes)

class GradeItemViewHolder(private val binding: GradeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var onAlertClick: ((String) -> Unit)? = null
    fun bind(gradeItemData: GradeItemData) {
        binding.alert.setOnClickListener {
            onAlertClick?.invoke(gradeItemData.about)
        }

        binding.gradeUsd.text = String.format("%.2f $", gradeItemData.total)
        binding.gradePercent.text = String.format("%.2f %%", gradeItemData.raise)
        binding.inStocksValue.text = String.format("%.2f $", gradeItemData.stock)
        binding.remainingUsdValue.text = String.format("%.2f $", gradeItemData.balanceUDS)
        binding.remainingRubValue.text = String.format("%.2f ₽", gradeItemData.balanceRUB)
    }
}

data class GradeItemData(
    var total: Float, var raise: Float, var stock: Float, var balanceUDS: Float,
    var balanceRUB: Float, var about: String
) : CardItemData(CardsRecyclerAdapter.CardsItemTypes.GRADE)

class RefillItemViewHolder(private val binding: RefillItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var onAlertClick: ((String) -> Unit)? = null

    fun bind(refillItemData: RefillItemData) {
        binding.alert.setOnClickListener {
            onAlertClick?.invoke(refillItemData.about)
        }
        binding.total.text = String.format("%.2f $", refillItemData.total)
        binding.withdrawnUsd.text = String.format("%.2f $", refillItemData.withdrawn)
        binding.refillRubValue.text = String.format("%.2f ₽", refillItemData.refillRUB)
        binding.refillUsdValue.text = String.format("%.2f $", refillItemData.refillUSD)

    }
}

data class RefillItemData(
    var total: Float, var withdrawn: Float, var refillUSD: Float,
    var refillRUB: Float, var about: String
) : CardItemData(CardsRecyclerAdapter.CardsItemTypes.REFILL)

class ProfitItemViewHolder(private val binding: ProfitItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var onAlertClick: ((String) -> Unit)? = null
    fun bind(profitItemData: ProfitItemData) {
        binding.alert.setOnClickListener {
            onAlertClick?.invoke(profitItemData.about)
        }
        binding.profitUsd.text = String.format("%.2f $", profitItemData.total)
        binding.profitPercent.text = String.format("%.2f %%", profitItemData.raise)
        binding.investedValue.text = String.format("%.2f $", profitItemData.invest)
        binding.totalPriceValue.text = String.format("%.2f $", profitItemData.price)
    }
}

data class ProfitItemData(
    var total: Float, var raise: Float, var invest: Float,
    var price: Float, var about: String
) : CardItemData(CardsRecyclerAdapter.CardsItemTypes.PROFIT)


class BonusItemViewHolder(private val binding: BonusItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var onAlertClick: ((String) -> Unit)? = null
    fun bind(bonusItemData: BonusItemData) {
        binding.alert.setOnClickListener {
            onAlertClick?.invoke(bonusItemData.about)
        }

        binding.totalPrice.text = String.format("%10.2f ₽", bonusItemData.totalRUB)
        binding.inTeamValue.text = String.format("%d ", bonusItemData.team)
        binding.refillRubValue.text = String.format("%.2f ₽", bonusItemData.refillRUB)
        // не понял почему в api приходит refillUSD а в макете есть место лишь для "Выведено RUB"
        binding.withdrawnRubValue.text = String.format("%.2f ₽", bonusItemData.refillUSD)
        binding.rub.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.totalPrice.text = String.format("%.2f ₽", bonusItemData.totalRUB)
        }

        binding.usd.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.totalPrice.text = String.format("%.2f $", bonusItemData.totalUSD)
        }

    }
}

data class BonusItemData(
    var totalRUB: Float, var totalUSD: Float, var team: Int,
    var refillUSD: Float, var refillRUB: Float, var about: String
) : CardItemData(CardsRecyclerAdapter.CardsItemTypes.BONUS)