package de.agiledojo.kata.checkoutservice.application.domain.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BundleCombinerTest {
    
    private val combiner = BundleCombiner()

    @Test
    fun `empty basket has no combinations`() {
        combiner.findBundleCombinations(emptyMap()) shouldBe emptySet()
    }
    
        @Test
    fun `multiple copies of a single volume in basket combine to multiple single book combinations`() {
        combiner.findBundleCombinations(basketOf(
            PotterVolume.I to Amount(2)
        )) shouldBe setOf(
            combinationOf(Bundle(1) to Amount(2))
        )

    }

    @Test
    fun `two different volumes in basket can be combined to single and two book combinations`() {
        combiner.findBundleCombinations(basketOf(
            PotterVolume.I to Amount(1),
            PotterVolume.II to Amount(1)
        )) shouldBe setOf(
            combinationOf(Bundle(1) to Amount(2)),
            combinationOf(Bundle(2) to Amount(1)) 
        )
    }

    @Test
    fun `a combination can consist of multiple bundles`() {
        combiner.findBundleCombinations(basketOf(
            PotterVolume.I to Amount(1),
            PotterVolume.II to Amount(2)
        )) shouldBe setOf(
            combinationOf(Bundle(1) to Amount(3)),
            combinationOf(
                Bundle(2) to Amount(1),
                Bundle(1) to Amount(1)
                ) )
    }

    @Test
    fun `there can be multiple combinations with the same size`() {
        combiner.findBundleCombinations(basketOf(
            PotterVolume.I to Amount(2),
            PotterVolume.II to Amount(2)
        )) shouldBe setOf(
            combinationOf(Bundle(1) to Amount(4)),
            combinationOf(
                Bundle(2) to Amount(2)
            ) )
    }

   @Test 
    fun `a complex basket is split into all possible combinations`() {
        combiner.findBundleCombinations(basketOf(
            PotterVolume.I to Amount(1),
            PotterVolume.II to Amount(2),
            PotterVolume.III to Amount(1)
        )) shouldBe setOf(
            combinationOf(Bundle(3) to Amount(1), Bundle(1) to Amount(1)),
            combinationOf(Bundle(1) to Amount(4)),
            combinationOf(Bundle(2) to Amount(2)) 
        )
    }
    
    private fun basketOf(vararg entry: Pair<PotterVolume, Amount>) = mapOf(*entry)
    private fun combinationOf(vararg entry: Pair<Bundle, Amount>) = mapOf(*entry)

}