 fun main(args: Array<String>) {
     val baseSchedule = mapOf("A-B" to 2, "B-C" to 2, "C-D" to 2, "D-E" to 2, "E-F" to 2)
    val maintenance = mapOf("C-D" to 1)
    val eventRoutes = listOf("B-C", "C-D", "D-E")
    val currentHour = 8
}

fun isPeakHour(hour:Int): Boolean{
    return (hour in 7..10)||(hour in 17..20)
}
fun adjustSchedule(baseSchedule:Map<String,Int>, maintenance:Map<String, Int>,hour: Int,eventRoutes: List<String>):Map<String, Int>{
    val newSchedule = mutableMapOf<String, Int>()
    val isPeak = isPeakHour(hour)
    

    for ((route,baseCount) in baseSchedule){
        var adjusted = baseCount
        var message = "Route $route starts with $adjusted vehicles"

    if(isPeak) {
        adjusted += 1
        message += "Added 1 for peak hour"
    }
        if(eventRoutes.contains(route)){
            adjusted+=1
            message+="Added 1 for special events"
        }
        if (maintenance.containsKey(route)){
            val forMaint = maintenance[route]?:0
            adjusted-=1
            message+="subtracted $forMaint for maintenance"
        }
    }
}