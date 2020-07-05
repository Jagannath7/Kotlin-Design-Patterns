package com.codingblocks.carpicker.cli

import com.codingblocks.carpicker.vehicle.Vehicle
import com.codingblocks.carpicker.vehicle.parts.*
import com.github.ajalt.clikt.core.UsageError
import com.github.ajalt.clikt.output.TermUi

class App {

    private val vehicleBuilder = Vehicle.Builder()
    private val wheelBaseBuilder = WheelBase.Builder()
    private val chasisBuilder = Chasis.Builder()
    private val engineBuilder = Engine.Builder()

    val welcomeString
        get() = """
            | ------- Welcome to Car Picker 1.0 ---------
            | Here you can build your very own car, with 
            | with everything fully customisable. Feel free
            | to pick engine, seats, wheels etc.
            """.trimMargin()


    fun prepareWheelBaseBuilder() {
        TermUi.prompt(
            "Enter wheelbase size: (S)mall, (M)edium or (L)arge"
        ) {
            when (it) {
                "S", "s" -> wheelBaseBuilder.setSize(WheelBase.Size.SMALL)
                "M", "m" -> wheelBaseBuilder.setSize(WheelBase.Size.MEDIUM)
                "L", "l" -> wheelBaseBuilder.setSize(WheelBase.Size.LARGE)
                else -> throw UsageError("Size has to be S, M or L")
            }
        }!!

        TermUi.prompt(
            "Enter type of wheels: (S)teel, (A)lloy, (C)arbon-fibre"
        ) {
            when (it) {
                "S", "s" -> wheelBaseBuilder.setWheelFactory(Wheel.Factory(Wheel.Type.STEEL))
                "A", "a" -> wheelBaseBuilder.setWheelFactory(Wheel.Factory(Wheel.Type.ALLOY))
                "C", "c" -> wheelBaseBuilder.setWheelFactory(Wheel.Factory(Wheel.Type.CARBONFIBRE))
                else -> throw UsageError("Wheels have to be S, A or C")
            }
        }

    }

    fun prepareChasisBuilder() {
        TermUi.prompt(
            "Enter chasis type: (H)atchback, (S)edan, SU(V) or (P)ickup "
        ) {
            when (it) {
                "H", "h" -> chasisBuilder.setChasisType(Chasis.Type.HATCHBACK)
                "S", "s" -> chasisBuilder.setChasisType(Chasis.Type.SEDAN)
                "V", "v" -> chasisBuilder.setChasisType(Chasis.Type.SUV)
                "P", "p" -> chasisBuilder.setChasisType(Chasis.Type.PICKUP)
                else -> throw UsageError("Size has to be S, M or L")
            }
        }!!
        TermUi.prompt(
            "Enter seat fabric: (C)loth, (R)exine, (L)eather"
        ) {
            when (it) {
                "C", "c" -> chasisBuilder.setSeatFactory(Seat.Factory(Seat.Upholstery.CLOTH))
                "R", "r" -> chasisBuilder.setSeatFactory(Seat.Factory(Seat.Upholstery.REXINE))
                "L", "l" -> chasisBuilder.setSeatFactory(Seat.Factory(Seat.Upholstery.LEATHER))
                else -> throw UsageError("Seat fabric has to be C, R or L")

            }
        }
    }

    fun prepareEngineBuilder() {
        engineBuilder.setEngineType(Engine.Type.DIESEL)
        engineBuilder.setTransmission(Transmission(Transmission.Type.FWD))

    }

    fun buildVehicle(): Vehicle = vehicleBuilder
        .setWheelBase(wheelBaseBuilder.build())
        .setChasis(chasisBuilder.build())
        .setEngine(engineBuilder.build())
        .build()

}