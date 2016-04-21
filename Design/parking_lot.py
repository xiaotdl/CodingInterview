# OO design

class Vehicle(object):
    mID = 0
    mParkingSize = 0

    def parkin(parkingManager):
        pass

    def moveout(parkingManager):
        pass


class Motorcycle(Vehicle):
    mParkingSize = 1


class Car(Vehicle):
    mParkingSize = 1


class Bus(Vehicle):
    mParkingSize = 1


class ParkingSpot(object):
    mID = 0
    mParkingSpotSize = 0
    mAvailable = False


class ParkingLot(object):
    mID = 0
    mParkingSpotList = []


class ParkingManager(object):
    mParkingLots = []

    def findParkingSpot(size):
        pass

    def generateParkingTicket(vehicle, parkingSpot):
        pass

    def withdrawParkingTicket(parkingTicketID):
        pass


class ParkingTicket(object):
    mID = 0
    mParkingSpot = None
    mVehicle = None
    mEnterTime = None
    mLeaveTime = None
