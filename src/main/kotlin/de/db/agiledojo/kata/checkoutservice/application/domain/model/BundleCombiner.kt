package de.db.agiledojo.kata.checkoutservice.application.domain.model

typealias Basket = Map<PotterVolume, Amount>
typealias BundleCombination = Map<Bundle, Amount>

class BundleCombiner {
    fun findBundleCombinations(basket: Basket) =
        (1..basket.maxBundleSize())
            .map { basket.bundleCombinationWithMaximumBundleSize(it) }
            .toSet()
   
    private fun Basket.maxBundleSize() = this.keys.size

    private fun Basket.bundleCombinationWithMaximumBundleSize(maxBundleSize: Int) =
        this.splitIntoBundles(maxBundleSize)
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, count) -> Amount(count) }
    
    private fun Basket.splitIntoBundles(maxSeriesSize: Int): List<Bundle> =
        if (this.isNotEmpty())
            this.selectVolumesForNextBundle(maxSeriesSize).let { volumes ->
                listOf(Bundle(volumes.size)) + this.removeVolumes(volumes).splitIntoBundles(maxSeriesSize)
            }
        else
            emptyList()

    private fun Basket.selectVolumesForNextBundle(maxSeriesSize: Int) =
        if (this.keys.size > maxSeriesSize)
            this.keys.toList().subList(0, maxSeriesSize).toSet()
        else
            this.keys
    
    private fun Basket.removeVolumes(volumesToRemove: Set<PotterVolume>): Basket =
        this.filterNot(isSingleCopyOf(volumesToRemove))
            .mapValues(decreaseNumberOfCopiesFor(volumesToRemove))
            .toMap()

    private fun isSingleCopyOf(volumes: Set<PotterVolume>): (Map.Entry<PotterVolume, Amount>) -> Boolean =
        { (volume, amount) ->
            volumes.contains(volume) && amount.value == 1
        }

    private fun decreaseNumberOfCopiesFor(volumes: Set<PotterVolume>): (Map.Entry<PotterVolume, Amount>) -> Amount =
        { (volume, amount) ->
            if (volumes.contains(volume))
                Amount(amount.value - 1)
            else
                amount
        }
}