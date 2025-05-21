 fun main(args: Array<String>) {
    
}

fun isPeakHour(hour:Int): Boolean{
    return (hour in 7..10)||(hour in 17..20)
}
fun adjustSchedule(baseSchedule:Map<String,Int>, maintenance:Map<String, Int>,hour: Int,eventRoutes: List<String>):Map<String, Int>{
    val newSchedule = mutableMapOf<String, Int>()
    val isPeak = isPeakHour(hour)
    println("cu")

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
            val for_maint = maintenance[route]?:0
            adjusted-=1
            message+="subtracted $for_maint for maintenance"
        }
    }
}