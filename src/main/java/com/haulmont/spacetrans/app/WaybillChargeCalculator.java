package com.haulmont.spacetrans.app;

import com.haulmont.spacetrans.entity.CustomerGrade;
import com.haulmont.spacetrans.entity.Discount;
import com.haulmont.spacetrans.entity.Waybill;
import com.haulmont.spacetrans.entity.WaybillItem;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.math.BigDecimal;

@Component("st_WaybillChargeCalculator")
public class WaybillChargeCalculator {

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    @Autowired
    private DataManager dataManager;

    /**
     * @return true if item charge was changed
     */
    public boolean calculateItemCharge(WaybillItem item) {
        BigDecimal charge = BigDecimal.ZERO;
        if (item.getDim() != null && item.getDim().getHeight() != null && item.getDim().getWidth() != null &&
                item.getDim().getLength() != null && item.getWeight() != null) {
            charge = BigDecimal.valueOf(item.getDim().getHeight())
                    .multiply(BigDecimal.valueOf(item.getDim().getWidth()))
                    .multiply(BigDecimal.valueOf(item.getDim().getLength()))
                    .multiply(BigDecimal.valueOf(0.1))
                    .add(BigDecimal.valueOf(item.getWeight()));
        }
        if (!charge.equals(item.getCharge())) {
            item.setCharge(charge);
            return true;
        }
        return false;
    }

    public void calculateWaybillTotals(Waybill waybill) {
        double totalWeight = 0;
        BigDecimal totalCharge = BigDecimal.ZERO;
        for (WaybillItem item : waybill.getItems()) {
            if (item.getWeight() != null) {
                totalWeight += item.getWeight();
            }
            if (item.getCharge() != null) {
                totalCharge = totalCharge.add(item.getCharge());
            }
        }
        BigDecimal totalChargeWithDiscount = totalCharge.multiply(getRateForGrade(waybill.getShipper().getGrade()));
        waybill.setTotalWeight(totalWeight);
        waybill.setTotalCharge(totalChargeWithDiscount.doubleValue());
    }


    private BigDecimal getRateForGrade(@Nullable CustomerGrade grade) {
        if (grade == null) {
            return BigDecimal.ONE;
        }
        return dataManager.load(Discount.class)
                .condition(PropertyCondition.equal("grade", grade))
                .optional()
                .map(Discount::getValue)
                .map(value -> HUNDRED.subtract(value).divide(HUNDRED))
                .orElse(BigDecimal.ONE);
    }

}
