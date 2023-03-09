


export interface INotification {
    count: number,
    active: boolean
}

const Notification: React.FC<INotification> = ({count,active}) => {
  return (
      <div className={"bg-[#ffa13e] items-center rounded-sm hover:cursor-pointer text-center text-xs text-white w-4 h-4"}>{count}
      </div>

)};

export default Notification;
