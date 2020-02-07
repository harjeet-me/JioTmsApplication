import { Moment } from 'moment';
import { IInvoice } from 'app/shared/model/invoice.model';
import { IContainer } from 'app/shared/model/container.model';
import { StatusEnum } from 'app/shared/model/enumerations/status-enum.model';
import { HAZMAT } from 'app/shared/model/enumerations/hazmat.model';
import { COVEREDBY } from 'app/shared/model/enumerations/coveredby.model';
import { LoadType } from 'app/shared/model/enumerations/load-type.model';
import { SizeEnum } from 'app/shared/model/enumerations/size-enum.model';

export interface ITrip {
  id?: number;
  tripNumber?: string;
  description?: string;
  shipmentNumber?: string;
  bol?: string;
  pickup?: Moment;
  drop?: Moment;
  currentLocation?: string;
  status?: StatusEnum;
  detention?: number;
  chasisInTime?: Moment;
  podContentType?: string;
  pod?: any;
  hazmat?: HAZMAT;
  recievedBy?: string;
  coveredBy?: COVEREDBY;
  loadType?: LoadType;
  containerSize?: SizeEnum;
  numbersOfContainer?: number;
  comments?: string;
  pickupLocationAddress?: string;
  pickupLocationId?: number;
  dropLocationAddress?: string;
  dropLocationId?: number;
  invoices?: IInvoice[];
  containers?: IContainer[];
  customerEmail?: string;
  customerId?: number;
  driverId?: number;
  equipmentId?: number;
  ownerOperatorId?: number;
}

export class Trip implements ITrip {
  constructor(
    public id?: number,
    public tripNumber?: string,
    public description?: string,
    public shipmentNumber?: string,
    public bol?: string,
    public pickup?: Moment,
    public drop?: Moment,
    public currentLocation?: string,
    public status?: StatusEnum,
    public detention?: number,
    public chasisInTime?: Moment,
    public podContentType?: string,
    public pod?: any,
    public hazmat?: HAZMAT,
    public recievedBy?: string,
    public coveredBy?: COVEREDBY,
    public loadType?: LoadType,
    public containerSize?: SizeEnum,
    public numbersOfContainer?: number,
    public comments?: string,
    public pickupLocationAddress?: string,
    public pickupLocationId?: number,
    public dropLocationAddress?: string,
    public dropLocationId?: number,
    public invoices?: IInvoice[],
    public containers?: IContainer[],
    public customerEmail?: string,
    public customerId?: number,
    public driverId?: number,
    public equipmentId?: number,
    public ownerOperatorId?: number
  ) {}
}
