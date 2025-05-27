class DynamicRoutingSystem {
    constructor() {
        this.publicLines = [];
        this.specialEvents = [];
        this.maintenance = new Map();
    }
    addTransportLine(line) {
        this.publicLines.push({
            ...line,
            baseInterval: line.interval,
            currentInterval: line.interval
        });
    }

    isPeakHour(time) {
        const hour = time.getHours();
        return (hour >= 7 && hour < 9) || (hour >= 17 && hour < 19);
    }

    isSpecialEvent(time) {
        return this.specialEvents.some(event =>
            event.date.toDateString() === time.toDateString()
        );
    }

    findLowestDemandTime() {
        
        return { start: "23:00", end: "05:00" };
    }

    getOtherRoutes(lineId) {
        return this.publicLines.filter(line => line.id !== lineId).map(line => line.id);
    }

    adjustSchedules(time) {
        const isPeak = this.isPeakHour(time);
        this.publicLines.forEach(line => {
            let demandFactor = isPeak ? 0.7 : 1.3;
            if (this.isSpecialEvent(time)) {
                demandFactor *= 0.5;
            }
            line.currentInterval = Math.min(
                Math.max(line.baseInterval * demandFactor, 5),
                30
            );
        });
    }

    scheduleMaintenance(lineId, duration) {
        const bestSlot = this.findLowestDemandTime();
        this.maintenance.set(lineId, {
            start: bestSlot.start,
            end: bestSlot.end,
            duration: duration,
            affectedLines: this.getOtherRoutes(lineId)
        });
    }
} 

const system = new DynamicRoutingSystem();
system.addTransportLine({ id: 1, name: "Super Metro", interval: 15 });
system.specialEvents.push({ date: new Date(), name: "Sol Concert" });
system.adjustSchedules(new Date());
system.scheduleMaintenance(1, 2);

console.log(system.publicLines);
console.log(system.maintenanceSchedule);
