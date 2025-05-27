def optimise_routing_systems(bus,train,peak_hours,off_peak_hours,special_events_hours,routes):
    for hour in peak_hours:
        if hour == special_events_hours and hour == routes:
            print(f"Assign ${train}")
        else:
            print(f"Assign ${bus}")
    
    for hour in off_peak_hours:
        if hour == special_events_hours and hour != routes:
            print(f"Assign ${bus}")
        else:
            print(f"Assign ${train}")

optimise_routing_systems("Matatu","Ilala",[10,12,14,16],[6,11,13,15],[12,13,14,20],["a","b","c","d"])
